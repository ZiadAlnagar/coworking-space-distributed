package Client;

import GUI.G_AddWorkspace;
import GUI.G_AddWorkspace_panel;
import RMI.WorkspaceInterface;
import Utility.Globals;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddWorkspace {

    G_AddWorkspace gui;
    Registry r;

    public AddWorkspace(G_AddWorkspace G_AddWs, Registry r) {
        gui = G_AddWs;
        this.r = r;
        gui.getAdd().addActionListener(new AddBtnAction());
        JComboBox status = gui.getStatus();
        status.addItem(Globals.WorkspaceStatus.AVAILABLE);
        status.addItem(Globals.WorkspaceStatus.OCCUPIED);
        status.addItem(Globals.WorkspaceStatus.SHARED);
        status.addItem(Globals.WorkspaceStatus.UNDER_MAINTENANCE);
        status.setSelectedItem(Globals.WorkspaceStatus.AVAILABLE);
    }

  

    class AddBtnAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                WorkspaceInterface ws = (WorkspaceInterface) r.lookup("workspace");
                String id = (String) gui.getId().getText();
                int level = Integer.parseInt(gui.getLevel().getSelectedItem().toString());
                String status = gui.getStatus().getSelectedItem().toString();
                String services = String.valueOf(gui.getServices().getSelectedValue());
                ws.setId(id);
                ws.setLevel(level);
                ws.InsertWorkSpace();
                
             
                

            } catch (RemoteException ex) {
                Logger.getLogger(AddWorkspace.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NotBoundException ex) {
                Logger.getLogger(AddWorkspace.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
