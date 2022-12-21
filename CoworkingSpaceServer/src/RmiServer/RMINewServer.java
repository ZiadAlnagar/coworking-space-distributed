package RmiServer;

import RMI.Account;
import RMI.IAccount;
import RMI.WorkspaceInterface;
import Server.Workspace;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class RMINewServer {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        

        IAccount a = new Account();
        WorkspaceInterface rrr = new Workspace();

      
        Registry r = LocateRegistry.createRegistry(3050);
        
   
        r.bind("acc", a);
        
        r.bind("workspace", rrr);
        System.out.println("Server is ready to be connected");
        
        
    }  
}

