package Server;

import RMI.WorkspaceProxy;
import Interface.PaymentMethod;
import RMI.WorkspaceInterface;
import Utility.Globals.*;
import java.time.Duration;
import java.time.LocalDateTime;

public class Reservation {
    private int _id;
    private LocalDateTime dateTime;    
    private Customer customerReserved;
    private Receptionist receptionistConfirmed;
    private WorkspaceProxy workspace;
    private LocalDateTime reservedFrom;
    private LocalDateTime reservedTo; 
    private boolean isShared;
    private ReservationStatus status; // RESERVED, ONGOING, COMPLETED, CANCELED
    private float totalPrice;
    private Payment paymentProcess;
    
    
    // Constructors
    public Reservation(int _id) {
        this._id = _id;
    }

    // Setters & Getters
    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public float getTotalPrice() {
        calcTotalPrice();
        return totalPrice;
    }

    public LocalDateTime getReservedFrom() {
        return reservedFrom;
    }

    public void setReservedFrom(LocalDateTime reservedFrom) {
        this.reservedFrom = reservedFrom;
        if(this.reservedTo.isBefore(reservedFrom))
            setReservedTo(reservedFrom);
    }

    public LocalDateTime getReservedTo() {
        return reservedTo;
    }

    public void setReservedTo(LocalDateTime reservedTo) {
        // check if the reservedTo time is valid
        if(reservedTo.isAfter(this.reservedFrom))
            this.reservedTo = reservedTo;
        // if not set a default time of duration 1 hour
        else
        {
            this.reservedTo = this.reservedFrom.plusHours(1);
            System.out.println("reservedTo DateTime not valid, by Default the Reservation Duration set to 1 hour");            
        }
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime() { 
        this.dateTime = LocalDateTime.now();
    }
    
    public void setDateTime(LocalDateTime dt) { 
        this.dateTime = dt;
    }

    public boolean isShared() {
        return isShared;
    }

    public void setShared() {
        // Check if the WS could be Shared
        if(workspace.getType().getName() != WorkspaceName.DEDICATED_DESK
        && workspace.getType().getName() != WorkspaceName.OPEN_WORKSPACE)
            this.isShared = true;
        else
        {
            this.isShared = false;
            System.out.println("Workspace cannot be Shared!");
        }
    }
    
    public void setNotShared() {
        // Check if the WS is Not already Shared
        if(workspace.getStatus() != WorkspaceStatus.SHARED)
            this.isShared = false;
        else
        {
            this.isShared = true;
            System.out.println("Workspace is already Shared!");
        }
    }
    
    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }
    
    public Customer getCustomerReserved() {
        return customerReserved;
    }

    public void setCustomerReserved(Customer customerReserved) {
        this.customerReserved = customerReserved;
    }

    public Receptionist getReceptionistConfirmed() {
        return receptionistConfirmed;
    }

    public void setReceptionistConfirmed(Receptionist receptionistConfirmed) {
        this.receptionistConfirmed = receptionistConfirmed;
    }

    public WorkspaceInterface getWorkspace() {
        return workspace;
    }

    public void setWorkspace(WorkspaceProxy workspace) {
        this.workspace = workspace;
    }

    public Payment getPaymentProcess() {
        return paymentProcess;
    }

    public void setPaymentProcess(Payment paymentProcess) {
        this.paymentProcess = paymentProcess;
    }
    
    
    // Methods
    public long calcDuration() {
        Duration d = Duration.between(reservedFrom, reservedTo);
        return d.toHours();
    }
    
    public float calcTotalPrice() {
        // Price = WS Cost/hr * Reserved Duration
        totalPrice = workspace.calcCostPerHour() * calcDuration();
        
        // If Shared, Discount 25%
        if(isShared)
            totalPrice *= (float)(1 - 0.25); 
        
        return this.totalPrice;
    }  
    
    public boolean makeReservation(Customer cust, Receptionist recp, Workspace ws, LocalDateTime from, LocalDateTime to, boolean isShared, PaymentMethod paymentMethod, float amountPaid) {
        this.customerReserved = cust;
        this.receptionistConfirmed = recp;
        
        if(!ws.isAvailable())
        {
            System.out.println("This Workspace is not Available now");
            return false;
        }
        if(ws.isAccessed())
        {
            System.out.println("This Workspace is already Accessed");
            return false;
        }
        
        this.workspace = new WorkspaceProxy(ws);
        this.reservedFrom = from;
        setReservedTo(to);
        if(isShared)
            setShared();
        else
            setNotShared();
        this.totalPrice = calcTotalPrice();
        if(amountPaid < this.totalPrice)
        {
            System.out.println("Paid Amount is not enough");
            return false;
        }       
        this.paymentProcess = new Payment(this._id, paymentMethod, amountPaid, LocalDateTime.now());
        this.paymentProcess.executeStrategy();
        
        this.status = ReservationStatus.RESERVED;
        this.dateTime = LocalDateTime.now();
        
        this.workspace.reserveWorkspace();
        
        //Add in DB
//        insertReservation();
        return true;
    }
    
    public boolean updateReservation(Customer cust, Receptionist recp, WorkspaceProxy ws, LocalDateTime from, LocalDateTime to, boolean isShared) {
        if(this.status != null)
        {
            System.out.println("Reservation cannot be updated");
            return false;
        }
        
        this.customerReserved = cust;
        this.receptionistConfirmed = recp;
        if(ws.isAvailable())
            this.workspace = ws;
        else
        {
            System.out.println("Workspace is not Available");
            return false;
        }           
        this.reservedFrom = from;
        setReservedTo(to);
        if(isShared)
            setShared();
        else
            setNotShared();
      
        //Update in DB
//        updateReservation();
        return true;
    }
    
    public void startReservation() {
        this.status = ReservationStatus.ONGOING;
        System.out.println("Reservation " + _id + " is Started now ");
    }
    public void cancelReservation() {
        if(this.status == ReservationStatus.RESERVED)
        {
            this.status = ReservationStatus.CANCELED;
            System.out.println("Reservation " + _id + " is Canceled");
        }
        else
            System.out.println("Reservation cannot be canceled now!");
    }
    public void completeReservation() {
        this.status = ReservationStatus.COMPLETED;
        this.workspace.leaveWorkspace();
        System.out.println("Reservation " + _id + " has Completed");
    }
    
    public Reservation loadReservation() {
        ReservationMapper rm = new ReservationMapper(this);
        return (Reservation) rm.loadReservation();
    }
    
    public void insertReservation() {
        ReservationMapper rm = new ReservationMapper(this);
        rm.insertReservation();
    }
    
    public void updateReservation() {
        ReservationMapper rm = new ReservationMapper(this);
        rm.updateReservation();
    }
    
    public void deleteReservation() {
        ReservationMapper rm = new ReservationMapper(this);
        rm.deleteReservation();
    }
}
