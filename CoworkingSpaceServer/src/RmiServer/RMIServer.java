package RmiServer;


import RMI.Account;
import RMI.IAccount;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {

    public static void main(String[] args) throws AlreadyBoundException, RemoteException {

        // My remote object [Skeleton]
       // WorkspaceInterface rrr = new Workspace();
        IAccount a = new Account();

        // My RMI Registry
        Registry registry = LocateRegistry.createRegistry(3050);
        //Add my object to the RMI Registry

        // registry.bind("workspace", rrr);
        registry.bind("acc", a);

        //  System.out.println("My facade is ready...");
        System.out.println("Server is ready to be connected");

    }
}
