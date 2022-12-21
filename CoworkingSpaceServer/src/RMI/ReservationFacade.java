package RMI;

import Server.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class ReservationFacade extends UnicastRemoteObject implements ReservationInterface {

    Reservation r;

    @Override
    public ReservationDTO getReservation() throws RemoteException {
        WorkspaceProxy w = (WorkspaceProxy) r.getWorkspace();
        Payment p = r.getPaymentProcess();
        ArrayList<RoomService> s = w.getServices();
        ArrayList<String> sname = new ArrayList<>();
        ArrayList<Float> sprice = new ArrayList<>();
        for (int i = 0; i < s.size(); i++) {
            sname.add(s.get(i).getName());
            sprice.add(s.get(i).getPrice());
        }

        ReservationDTO rr = new ReservationDTO(
                r.getId(),
                r.getDateTime(),
                w.getId(),
                w.getLevel(),
                w.getName(),
                w.getCapacity(),
                w.getDescription(),
                w.getCost(),
                sname,
                sprice,
                w.getStatus(),
                r.getReservedFrom(),
                r.getReservedTo(),
                r.isShared(),
                r.getStatus(),
                r.getTotalPrice(),
                p.getId(),
                p.getMethod(),
                p.getAmount()
        );
        return rr;
    }

    @Override
    public void setReservation(ReservationDTO rr) throws RemoteException {
        r.setId(rr.getId());
        r.setDateTime(rr.getDateTime());

        WorkspaceProxy w = (WorkspaceProxy) r.getWorkspace();
        w.setId(rr.getWorkspace_id());
        w.setLevel(rr.getLevel());
        w.getType().setName(rr.getName());
        w.getType().setCapacity(rr.getCapacity());
        w.getType().setDescription(rr.getDescription());
        w.getType().setCost(rr.getCost());

        ArrayList<RoomService> s = new ArrayList<>();
        for (int i = 0; i < rr.getServicesName().size(); i++) {
            s.add(new RoomService(rr.getServicesName().get(i), rr.getServicesprice().get(i)));
        }

        w.setServices(s);
        w.setStatus(rr.getWorkspaceStatus());
        r.setReservedFrom(rr.getReservedFrom());
        r.setReservedTo(rr.getReservedTo());
        if (rr.isIsShared()) {
            r.setShared();
        } else {
            r.setNotShared();
        }
        r.setStatus(rr.getReservationStatus());
        r.calcTotalPrice();

        Payment p = r.getPaymentProcess();
        p.setId(rr.getPayment_id());
        p.setMethod(rr.getMethod());
        p.setAmount(rr.getAmount());
    }
}