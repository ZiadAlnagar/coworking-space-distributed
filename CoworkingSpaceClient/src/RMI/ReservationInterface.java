package RMI;

import java.rmi.RemoteException;

interface ReservationInterface {
    public ReservationDTO getReservation() throws RemoteException;
    public void setReservation(ReservationDTO rr) throws RemoteException;
}
