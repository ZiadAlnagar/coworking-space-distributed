package Client;

import GUI.G_AddWorkspace_panel;
import RMI.WorkspaceInterface;
import Controllers.LoginController;
import GUI.G_AddWorkspace;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CoworkingSpaceClient {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        
        Registry r = LocateRegistry.getRegistry(3050);
        //gui
        G_AddWorkspace G_AddWs = new G_AddWorkspace();
        G_AddWs.setLocationRelativeTo(null);
        G_AddWs.setVisible(true);
      // AddWorkspace C_AddWorkspace = new AddWorkspace(G_AddWs, r);
     Login gui = new Login();
   gui.setLocationRelativeTo(null);  
       gui.setVisible(true);
       LoginController Lcontroller = new LoginController(gui, r);

       
    }
}
