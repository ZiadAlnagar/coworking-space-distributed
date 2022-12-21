package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

interface ReservationInterface1 extends Remote{
    public ReservationDTO getReservation() throws RemoteException;
    public void setReservation(ReservationDTO rr) throws RemoteException;
}
