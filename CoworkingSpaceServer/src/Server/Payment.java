package Server;

import Interface.PaymentMethod;
import java.time.LocalDateTime;


public class Payment {
    private int id;
    private PaymentMethod method;    
    private float amount;
    private LocalDateTime dateTime;
   
//    private Customer Cust;

    public Payment() {
    }
    
    public Payment(int id, PaymentMethod method, float amount, LocalDateTime dateTime) {
        this.id = id;
        this.method = method;
        this.amount = amount;
        this.dateTime = dateTime;   
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setDateTime() {
        this.dateTime = LocalDateTime.now();
    }
    
    public int getId() {
        return id;
    }

    public float getAmount() {
        return amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
    
    public void setMethod(PaymentMethod method) {
        this.method = method;
    }

    public PaymentMethod getMethod() {
        return method;
    }
      
    public void executeStrategy(){    
        method.MakePayment(this.amount);
    }
}
