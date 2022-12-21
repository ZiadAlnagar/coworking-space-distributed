
package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface IAccount extends Remote{
     public boolean login(String Email, String Password, char AccType) throws RemoteException;
     public void Register(String Email, String Password, String Fname, String Lname, String DOB, String PhoneNum) throws RemoteException;
}
