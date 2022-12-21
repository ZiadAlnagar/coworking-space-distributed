package RMI;



import Server.*;
import RMI.IAccount;
import static java.lang.Character.toLowerCase;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Objects;


public class Account extends UnicastRemoteObject implements IAccount {
    
    private String Email;
    private String Password;
    private String Fname;
    private String Lname;
    private String DOB;
    private String PhoneNum;


    public Account() throws RemoteException{
        
        this.Email = "";
        this.Password = "";
        this.Fname = "";
        this.Lname = "";
        this.DOB = "";
        this.PhoneNum = "";
    
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Account other = (Account) obj;
        if (!Objects.equals(this.Email, other.Email)) {
            return false;
        }
        if (!Objects.equals(this.Password, other.Password)) {
            return false;
        }
        if (!Objects.equals(this.Fname, other.Fname)) {
            return false;
        }
        if (!Objects.equals(this.Lname, other.Lname)) {
            return false;
        }
        if (!Objects.equals(this.DOB, other.DOB)) {
            return false;
        }
        return Objects.equals(this.PhoneNum, other.PhoneNum);
    }



    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setFname(String Fname) {
        this.Fname = Fname;
    }

    public void setLname(String Lname) {
        this.Lname = Lname;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public void setPhoneNum(String PhoneNum) {
        this.PhoneNum = PhoneNum;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public String getFname() {
        return Fname;
    }

    public String getLname() {
        return Lname;
    }

    public String getDOB() {
        return DOB;
    }

    public String getPhoneNum() {
        return PhoneNum;
    }


    @Override
    public void Register (String Email, String Password, String Fname, String Lname, String DOB, String PhoneNum) throws RemoteException  {
        this.Email = Email;
        this.Password = Password;
        this.Fname = Fname;
        this.Lname = Lname;
        this.DOB = DOB;
        this.PhoneNum = PhoneNum;
    }

    @Override
   public boolean login(String Email, String Password, char AccType) throws RemoteException {
     
      boolean check = false;
      
     char LowerCase = toLowerCase(AccType);
       
      switch(LowerCase){
          
          case 'a': 
          
            Admin a = new Admin();
             a  = a.getAdmintbByEmail(Email);
              if (a==null){
                  System.out.println("Email is wrong");
                  break;
                  
              }else{
                  
                  if (a.getAcc().getPassword().equals(Password)){
                      check = true;
                      System.out.println("Correct Email & Password");
                      return check;
                       
                  }
                  else
                  
                  System.out.println("Password is wrong");
                  break;
            
              }
                     
             case 'b': 
          
             Customer c = new Customer();
             c  = c.getCustomerbByEmail(Email);
              if (c==null){
                  System.out.println("Email is incorrect");
                  break;
                  
              }else{
                  
                  if (c.getAcc().getPassword().equals(Password)){
                      check = true;
                      System.out.println("Correct Email & Password");
                      return check;
                       
                  }
                  else
                  
                  System.out.println("Password is wrong");
                  break;
            
              }
             
                 case 'c': 
          
             Receptionist r = new Receptionist();
             r  = r.getReceptbByEmail(Email);
              if (r==null){
                  System.out.println("Email is incorrect");
                  break;
                  
              }else{
                  
                  if (r.getAcc().getPassword().equals(Password)){
                      check = true;
                      System.out.println("Correct Email & Password");
                      return check;
                       
                  }
                  else
                  
                  System.out.println("Password is wrong");
                  break;
            
              }
              
              default: System.out.println("Wrong Account Type ---> a: Admin, b:Customer, c:Receptionist"); 
              return false;
      }
            return check;
     
   }
   
    @Override
    public String toString() {
        return "Account{" + "Email=" + Email + ", Password=" + Password + ", Fname=" + Fname + ", Lname=" + Lname + ", DOB=" + DOB + ", PhoneNum=" + PhoneNum + '}';
    }
    
  
}
