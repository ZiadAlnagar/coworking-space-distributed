/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

//import Server.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import Utility.Globals.*;
import Interface.PaymentMethod;
import java.io.Serializable;

// Server
//(Reservation - Workspace - WorkspaceType - RoomService - Payment) DTO
public class ReservationDTO implements Serializable {
    private int _id;
    private LocalDateTime dateTime;    
    //private Customer customerReserved;
    //private Receptionist receptionistConfirmed;
    //Workspace ;
        private String workspace_id;
        private int level;
        //WorkspaceType ;
            private WorkspaceName name;
            private int capacity;
            private String description;
            private float cost;
        //Services ;
            private ArrayList<String> servicesName; // wifi, board, projector, computer
            private ArrayList<Float> servicesprice;
        private WorkspaceStatus workspaceStatus;    // avail, occup, shared, maint
    
    private LocalDateTime reservedFrom;
    private LocalDateTime reservedTo; 
    private boolean isShared;
    private ReservationStatus reservationStatus;    // RESERVED, ONGOING, COMPLETED, CANCELED
    private float totalPrice;   
    //Payment ;
        private int payment_id;
        private PaymentMethod method;
        private float amount;

    public ReservationDTO(int _id, LocalDateTime dateTime, String workspace_id, int level, WorkspaceName name, int capacity, String description, float cost, ArrayList<String> servicesName, ArrayList<Float> servicesprice, WorkspaceStatus workspaceStatus, LocalDateTime reservedFrom, LocalDateTime reservedTo, boolean isShared, ReservationStatus reservationStatus, float totalPrice, int payment_id, PaymentMethod method, float amount) {
        this._id = _id;
        this.dateTime = dateTime;
        this.workspace_id = workspace_id;
        this.level = level;
        this.name = name;
        this.capacity = capacity;
        this.description = description;
        this.cost = cost;
        this.servicesName = servicesName;
        this.servicesprice = servicesprice;
        this.workspaceStatus = workspaceStatus;
        this.reservedFrom = reservedFrom;
        this.reservedTo = reservedTo;
        this.isShared = isShared;
        this.reservationStatus = reservationStatus;
        this.totalPrice = totalPrice;
        this.payment_id = payment_id;
        this.method = method;
        this.amount = amount;
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getWorkspace_id() {
        return workspace_id;
    }

    public void setWorkspace_id(String workspace_id) {
        this.workspace_id = workspace_id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public WorkspaceName getName() {
        return name;
    }

    public void setName(WorkspaceName name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public ArrayList<String> getServicesName() {
        return servicesName;
    }

    public void setServicesName(ArrayList<String> servicesName) {
        this.servicesName = servicesName;
    }

    public ArrayList<Float> getServicesprice() {
        return servicesprice;
    }

    public void setServicesprice(ArrayList<Float> servicesprice) {
        this.servicesprice = servicesprice;
    }

    public WorkspaceStatus getWorkspaceStatus() {
        return workspaceStatus;
    }

    public void setWorkspaceStatus(WorkspaceStatus workspaceStatus) {
        this.workspaceStatus = workspaceStatus;
    }

    public LocalDateTime getReservedFrom() {
        return reservedFrom;
    }

    public void setReservedFrom(LocalDateTime reservedFrom) {
        this.reservedFrom = reservedFrom;
    }

    public LocalDateTime getReservedTo() {
        return reservedTo;
    }

    public void setReservedTo(LocalDateTime reservedTo) {
        this.reservedTo = reservedTo;
    }

    public boolean isIsShared() {
        return isShared;
    }

    public void setIsShared(boolean isShared) {
        this.isShared = isShared;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(int payment_id) {
        this.payment_id = payment_id;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    } 
}
