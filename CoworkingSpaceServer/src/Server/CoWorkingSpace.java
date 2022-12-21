package Server;

import RMI.Account;
import java.rmi.RemoteException;

public class CoWorkingSpace {

    public static void main(String[] args) throws RemoteException {

        Database DB = new Database();

        Account a1 = new Account();
        Account a2 = new Account();
        Account a3 = new Account();
        a1.Register("ahmed@gmial.com", "ss123", "Ahmed", "Mohamed", "12/31/1999", "01077885");
        a2.Register("salma@gmial.com", "sm123", "Salma", "Aymen", "4/15/2003", "0101010102");
        a3.Register("osama@gmial.com", "os123", "Osama", "Ibrahim", "3/27/1998", "0109987353");
        Receptionist Ahmed = new Receptionist(001, "873794739493", "Maadi", 3200, a1);
        Admin Salma = new Admin(01, 5500, 3, a2);
        Customer Osama = new Customer(1, 0, a3);
//      Ahmed.InsertReceptionist();
//
//        Salma.InsertAdmin();
//
//        Osama.InsertCustomer();
//        DB.getDatabaseNames();
        
 //       Osama.deleteCustomer();

         Salma.setControlDegree(1);
         
        Salma.updateAdmin();

        DB.CloseConnection();
        
//      DB.DropDatabase();
        
        
//      a3.login("osama@gmial.com", "123", 'b');

//        sudo mongod --dbpath /System/Volumes/Data/db
//        a2.login("ahmed@gmial.com", "s123", 'c');
//----------------------------------------------------------------
//    Second Run
//      Feedback feedback1=new Feedback();
//        new Admin(feedback1);
//        System.out.println("Admin feedback");	
//        feedback1.setFeedBack(1,"feedback test","feedback Description","10/10/2000","feedback type");
//          WorkingSpace w1=new WorkingSpace();
//          w1.setCapacity(10);
//          w1.setDescription("room");
//          w1.setType(WorkspaceType.Office_suite);
//          
//          w1.AddWorkspace("1", 1, serviceType.WIFI, WorkSpaceeStatus.shared);
//             w1.ViewWorkSpace();
// ------------------------------------------------------------------
//     Thrid Run
//        Order order = new Order();
//        order.makeOrder();
//        Customer Osama = new Customer(1, 0, a3);
//        Receptionist Ahmed = new Receptionist(001, "873794739493", "Maadi", 3200, a1);
//        Printer p = Printer.getInstance();
//        p.sendPrintRequest(Osama, Ahmed);
//        p.viewQueueList();
//------------------------------------------------------------------
// .   Fourth Run
    }

}
