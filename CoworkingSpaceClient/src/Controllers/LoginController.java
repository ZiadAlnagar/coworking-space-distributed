package Controllers;

import Client.Login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import RMI.IAccount;
import javax.swing.ButtonGroup;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
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
           
           
           
             ButtonGroup group = new ButtonGroup();
           
         
           
            JRadioButton a=   gui.getjRadioButton1();
            JRadioButton b=  gui.getjRadioButton3();
            JRadioButton c =  gui.getjRadioButton2();
          

           group.add(a);
           group.add(b);
           group.add(c);
           
           String res = group.getSelection().getActionCommand();
  
   
            try {
                
                IAccount ic = (IAccount) r.lookup("acc");
                switch (res) {
                   case "Admin":
                       ic.login(email, g, 'a');
                       break;
                   case "Receptionist":
                       ic.login(email, g, 'c');
                       break;
                   case "Customer":
                        ic.login(email, g, 'b');
                        break;
               }
          
                
                
            } catch (RemoteException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NotBoundException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
     //      gui.getjRadioButton1().getSelectedIcon()
        }
                
        
    
   
}
