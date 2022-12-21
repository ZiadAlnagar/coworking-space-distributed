package Controllers;

//import GUI.MakeReservation;
import RMI.*;
import Utility.Globals;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class makeReservationController {
//    MakeReservation gui;
    Registry registry;

    public makeReservationController(Registry registry) throws RemoteException, NotBoundException {
//        this.gui = gui;
        this.registry = registry;        
    }
    
    public void makeReservation() throws RemoteException, NotBoundException {
        ReservationInterface res = (ReservationInterface) registry.lookup("res");
        ArrayList<String> sname = new ArrayList<String>();
        sname.add("WIFI");
        ReservationDTO resDTO = new ReservationDTO(
                1000, 
                LocalDateTime.now(), 
                "100", 
                1, 
                Globals.WorkspaceName.OFFICE_SUITE, 
                10, 
                "some description about the workspace", 
                (float) 50.0, 
                sname, 
                new ArrayList<Float>(5), 
                Globals.WorkspaceStatus.AVAILABLE, 
                LocalDateTime.of(2022, 1, 1, 10, 0), 
                LocalDateTime.of(2022, 1, 1, 12, 0), 
                false, 
                null, 
                0, 
                1000, 
                null, 
                200);
        res.setReservation(resDTO);
    }
    
    public void viewReservation() throws RemoteException, NotBoundException {
        ReservationInterface res = (ReservationInterface) registry.lookup("res");
        ReservationDTO resDTO = res.getReservation();
        
        System.out.println("Reservation ID: " + resDTO.getId());
        System.out.println("Reservation Date and Time Reserved: " + resDTO.getDateTime());
        System.out.println("Reservation Status: " + resDTO.getReservationStatus());
        System.out.println("Reservation From: " + resDTO.getReservedFrom().toString());
        System.out.println("Reservation To: " + resDTO.getReservedTo().toString());
        System.out.println("  Workspace name: " + resDTO.getName());
        System.out.println("  Workspace Level: " + resDTO.getLevel());
        System.out.println("  Workspace capacity: " + resDTO.getCapacity());
        System.out.println("  Workspace Description: " + resDTO.getDescription());
        System.out.println("  Workspace Cost per Hour: " + resDTO.getCost());
        System.out.println("Total Price: " + resDTO.getTotalPrice());
    }
    
}