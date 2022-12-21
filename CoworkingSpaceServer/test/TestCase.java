/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */



import Server.Account;
import Server.Database;
import Server.Receptionist;
import Server.Reservation;
import Server.Workspace;
import Server.Account;
import Server.Admin;
import Server.Customer;
import Server.Receptionist;
import Server.Reservation;
import Server.RoomService;
import Server.Workspace;
import Server.WorkspaceStatus;
import Server.WorkspaceType;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestCase {


    @Test
    public void testRegisterCust() {
        //test is the Customer registered to the system or not
        Account acc = new Account();
        acc.Register("example@email", "password", "John", "Doe", "3/27/1998", "0123456");
        Customer user = new Customer(1, 0, acc);
        user.InsertCustomer();
        String test[]={"example@email", "password", "John", "Doe", "3/27/1998", "0123456"};
        assertEquals(test,user.getCustomerbByEmail("example@email"));
    }

    @Test
    public void testRegisterAdmin() {
        //test is the Admin registered to the system or not
        Account acc = new Account();
        acc.Register("example@email", "password", "John", "Doe", "3/27/1998", "0123456");
        Admin admin = new Admin(01, 5500, 3, acc);
        admin.InsertAdmin();
        String test[]={"example@email", "password", "John", "Doe", "3/27/1998", "0123456"};
        assertEquals(test,admin.getAdmintbByEmail("example@email"));
    }
    @Test
    public void testRegisterReceptionist() {
        //test is the user registered to the system or not
        Account acc = new Account();
        acc.Register("example@email", "password", "John", "Doe", "3/27/1998", "0123456");
        Receptionist recep = new Receptionist(001, "873794739493", "Maadi", 3200, acc);
        recep.InsertReceptionist();
        String test[]={"example@email", "password", "John", "Doe", "3/27/1998", "0123456"};
        assertEquals(test,recep.getReceptbByEmail("example@email"));
    }


    @Test
    public void testLogin() {
        //test if the user is registered to the system or not to login
        Account acc = new Account();
        acc.Register("example@email", "password", "John", "Doe", "3/27/1998", "0123456");
        acc.login("example@email", "123", 'b');
        boolean test = true;
        assertEquals(test,acc.login("example@email", "123", 'b'));
    }
    //String _id, int level, WorkspaceType type, ArrayList<RoomService> services, WorkspaceStatus status
    @Test
    public void testWorkSpace() {
        Workspace w1 =new Workspace();
        w1.setId("10");
        w1.InsertWorkSpace();
        w1.getWorkSpaceByID(w1.getId());
        String test = "10";
        assertEquals(test,"10");
    }
    @Test
    public void testWorkSpaceExist() {
        Workspace w1 =new Workspace();
        w1.setId("10");
        w1.InsertWorkSpace();
        w1.getWorkSpaceByID(w1.getId());
        w1.deleteWorkSpace();
        String test = null;
        assertEquals(test,w1.getWorkSpaceByID(w1.getId()));
    }
    @Test
    public void testInsertReservation() {
        Reservation r1 = new Reservation();
        r1.setId(1);
        r1.insertReservation();
        assertEquals(1,r1.loadReservation());
    }
    @Test
    public void testExistReservation() {
        Reservation r1 = new Reservation();
        r1.setId(1);
        r1.insertReservation();
        r1.deleteReservation();
        assertEquals(null,r1.loadReservation());
    }
    @Test
    public void testInsertreceptionist() {
        Account acc = new Account();
        acc.Register("example@email", "password", "John", "Doe", "3/27/1998", "0123456");
        Receptionist r1 = new Receptionist(001, "873794739493", "Maadi", 3200, acc);
        r1.InsertReceptionist();
        Receptionist r2 =r1.getReceptbByEmail("example@email");
        assertEquals(001,r2.getId());
    }

}