package Server;

import Interface.WorkspaceReadOnly;
import RMI.WorkspaceInterface;
import com.mongodb.client.model.Filters;
import java.util.ArrayList;
import org.bson.Document;
import Utility.Globals.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;

public class Workspace extends UnicastRemoteObject implements WorkspaceReadOnly, WorkspaceInterface {  
    private String _id;
    private int level;
    private WorkspaceType type;
    private ArrayList<RoomService> services;  // wifi, board, projector, computer
    private WorkspaceStatus status;     // avail, occup, shared, maint
    private boolean isAccessed = false;

    public Workspace(String _id, int level, WorkspaceType type, ArrayList<RoomService> services, WorkspaceStatus status) throws RemoteException{
        this._id = _id;
        this.level = level;
        this.type = type;
        this.services = services;
        this.status = status;
    }

    // Constructors
    public Workspace() throws RemoteException{
       
        this._id = "0";
        this.level = 0;
        this.type = null;
        this.services = null;
        this.status = WorkspaceStatus.AVAILABLE;
    }

//    public Workspace(String _id, int level, WorkspaceType type, ArrayList<RoomService> services, WorkspaceStatus status) {
//        this._id = _id;
//        this.level = level;
//        this.type = type;
//        this.services = services;
//        this.status = status;
//    }
       
    // Setters & Getters
    @Override
    public String getId() {
        return _id;
    }

    @Override
    public void setId(String _id) {
        this._id = _id;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
    }

//    @Override
    public WorkspaceType getType() {
        return type;
    }

    public void setType(WorkspaceType type) {
        this.type = type;
    }
    
    @Override
    public WorkspaceName getName() {
        return type.getName();
    }
    @Override
    public int getCapacity() {
        return type.getCapacity();
    }
    @Override
    public String getDescription() {
        return type.getDescription();
    }
    @Override
    public float getCost() {
        return type.getCost();
    }

    @Override
    public ArrayList<RoomService> getServices() {
        return services;
    }

    public void setServices(ArrayList<RoomService> services) {
        this.services = services;
    }
    
    @Override
    public WorkspaceStatus getStatus() {
        return status;
    }

    @Override
    public void setStatus(WorkspaceStatus status) {
        this.status = status;
    }

    public boolean isAccessed() {
        return this.isAccessed;
    }
    
    public void setAccessed() {
        this.isAccessed = true;
    }

    public void setNotAccessed() {
        this.isAccessed = false;
    }
    
    
    // Methods
    @Override
    public void reserveWorkspace() {
        this.status = WorkspaceStatus.OCCUPIED;
    }
    
    @Override
    public void leaveWorkspace() {
        this.status = WorkspaceStatus.AVAILABLE;
    }
    
//    @Override
    public void addServices(RoomService s) {
        this.services.add(s);
    }
    
    @Override
    public float calcCostPerHour(){
        //Add WS Type Cost
        float cost = type.getCost();
        //Add WS Services Cost
        for (int i = 0; i < services.size(); i++)
            cost += services.get(i).getPrice();        
        return cost;
    }
    
    @Override
    public boolean isAvailable() {
        return (status == WorkspaceStatus.AVAILABLE || status == WorkspaceStatus.SHARED);
    }

    @Override
    public void InsertWorkSpace() throws RemoteException {
        Database db = new Database();
        db.getWorkSpaceCollection().insertOne(Document.parse(db.getGson().toJson(this)));
        System.out.println("Workspace inserted");
        db.CloseConnection();
    }
    
    public Object getWorkSpaceByID(String id) throws RemoteException {
        Database db = new Database();
        Object doc = db.getWorkSpaceCollection().find(Filters.eq("_id", id)).first();
        //Workspace w = new Workspace();
        //w = (Workspace) doc;
        db.CloseConnection();
        return doc;
    }
    
    public void deleteWorkSpace() throws RemoteException {           
        Database db = new Database();
        db.getWorkSpaceCollection().deleteOne(Filters.eq("_id", this.getId()));
        System.out.println("Workspace Deleted");
        db.CloseConnection();
   }
}
