package Client;

import Utility.Globals;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface WorkspaceInterface extends Remote  {
    public String getId() throws RemoteException;

    public int getLevel() throws RemoteException;
    
//    public WorkspaceType getType();

    public Globals.WorkspaceName getName() throws RemoteException;
    
    public int getCapacity() throws RemoteException;

    public String getDescription() throws RemoteException;

    public float getCost() throws RemoteException;

//    public ArrayList<RoomService> getServices();

    public Globals.WorkspaceStatus getStatus() throws RemoteException;
    
    // Methods
    public void reserveWorkspace() throws RemoteException;
    
    public void leaveWorkspace() throws RemoteException;
    
//    public void addServices(RoomService s);
    
    public float calcCostPerHour() throws RemoteException;
    
    public boolean isAvailable() throws RemoteException;

    public void InsertWorkSpace() throws RemoteException;

    public void setId(String _id) throws RemoteException;

    public void setLevel(int level) throws RemoteException;

    public void setStatus(Globals.WorkspaceStatus status) throws RemoteException;
}
