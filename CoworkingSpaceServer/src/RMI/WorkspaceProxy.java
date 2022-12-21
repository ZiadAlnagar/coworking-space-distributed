package RMI;

import Server.RoomService;
import Server.Workspace;
import Server.WorkspaceType;
import Utility.Globals;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class WorkspaceProxy implements WorkspaceInterface{
    private Workspace workspace;
    private String _id;
    private int level;
    private WorkspaceType type;
    private ArrayList<RoomService> services;    // wifi, board, projector, computer, ..
    private Globals.WorkspaceStatus status;     // avail, occup, shared, maint
    
    // Constructors
    public WorkspaceProxy(Workspace workspace) {
        this.workspace = workspace;     
        this._id = workspace.getId();
        this.level = workspace.getLevel();
        this.type = workspace.getType();
        this.services = workspace.getServices();
        this.status = workspace.getStatus();
        
        if(this.workspace.isAccessed())
            this.workspace.setAccessed();
        else
            System.out.println("Try Again Later...");
    }
       
    // Setters & Getters
//    public Workspace getWorkspace() {
//        return workspace;
//    }   
    
    @Override
    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    @Override
    public int getLevel() {
        return level;
    }

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
    public Globals.WorkspaceName getName() {
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

//    @Override
    public ArrayList<RoomService> getServices() {
        return services;
    }

    public void setServices(ArrayList<RoomService> services) {
        this.services = services;
    }
    
    @Override
    public Globals.WorkspaceStatus getStatus() {
        return status;
    }

    public void setStatus(Globals.WorkspaceStatus status) {
        this.status = status;
    }
    
    // Methods
    @Override
    public void reserveWorkspace() {
        this.status = Globals.WorkspaceStatus.OCCUPIED;
    }
    
    @Override
    public void leaveWorkspace() {
        this.status = Globals.WorkspaceStatus.AVAILABLE;
    }
    
//    @Override
    public void addServices(RoomService s) {
        this.services.add(s);
    }
    
    @Override
    public float calcCostPerHour() {
        //Add WS Type Cost
        float cost = type.getCost();
        //Add WS Services Cost
        for (int i = 0; i < services.size(); i++)
            cost += services.get(i).getPrice();        
        return cost;
    }
    
    @Override
    public boolean isAvailable() {
        return (status == Globals.WorkspaceStatus.AVAILABLE || status == Globals.WorkspaceStatus.SHARED);
    }

    @Override
    public void InsertWorkSpace() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
