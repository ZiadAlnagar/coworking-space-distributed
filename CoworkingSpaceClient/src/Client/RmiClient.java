
package Client;

import GUI.G_AddWorkspace;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RmiClient {
    
  public static void main(String[] args) throws RemoteException {

     
//        Login gui = new Login();
        
    G_AddWorkspace ws = new G_AddWorkspace();
     ws.setLocationRelativeTo(null); 
        
        ws.setVisible(true);
//        gui.setLocationRelativeTo(null); 
//        
//        gui.setVisible(true);
        

        Registry r = LocateRegistry.getRegistry(3050);
        
        AddWorkspace wsc = new AddWorkspace(ws, r);
//        LoginController Lcontroller = new LoginController(gui, r);
        
        
        
    }
    
}
