package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import Client.RmiClient;
import GUI.Login;
import RMI.IAccount;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class LoginController  {
    
    Login gui;
    Registry r;
    
     public LoginController(Login gui, Registry r) {
        this.r = r;
        this.gui=gui;
        
    gui.getjButton4().addActionListener(new LoginDatabase());
    
    }
    
    
   
    class LoginDatabase implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent ae){
            
           String email = gui.getjTextField1().getText();
           String g =  gui.getjPasswordField1().getText();
           
           
         
           
           gui.getjRadioButton1().setSelected(true);
           
          
            try {
                IAccount ic = (IAccount) r.lookup("acc");
                ic.login(email, g, 'a');
                
            } catch (RemoteException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NotBoundException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
     //      gui.getjRadioButton1().getSelectedIcon()
        }
                
        
    
   
}
