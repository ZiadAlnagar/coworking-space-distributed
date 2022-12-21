package Server;

import Interface.ICustomer;
import RMI.Account;
import java.util.ArrayList;
import java.util.Objects;
import com.mongodb.client.model.Filters;
import org.bson.Document;


public class Customer implements ICustomer {
    private int _id;
    private double wallet;
    private Account Acc;
   // private ArrayList<Reservation> reservations;
    
    public Customer(int id, double wallet, Account Acc) {     
        this._id = id;
        this.wallet = wallet;
        this.Acc = Acc;
      //  this.reservations = new ArrayList<Reservation>();
    }
    
       public Customer() {
         this._id = 0;
        this.wallet = 0;
        this.Acc = null;
       // this.reservations=null;
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
        final Customer other = (Customer) obj;
        if (this._id != other._id) {
            return false;
        }
        if (Double.doubleToLongBits(this.wallet) != Double.doubleToLongBits(other.wallet)) {
            return false;
        }
        return Objects.equals(this.Acc, other.Acc);
    }
    
    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public double getWallet() {
        return wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    public Account getAcc() {
        return Acc;
    }

    public void setAcc(Account Acc) {
        this.Acc = Acc;
    }
    
    @Override
    public String toString() {
        return "Customer{" + "id=" + _id + ", wallet=" + wallet + ", Acc=" + Acc + '}';
    }
   
   
        public void InsertCustomer() {

        Database db = new Database();
        db.getCustCollection().insertOne(Document.parse(db.getGson().toJson(this)));
        System.out.println("Customer inserted.");
        db.CloseConnection();
        
    }
        
        
  public Customer getCustomerbByEmail(String email) {
            
        Database db = new Database();
        Customer c = new Customer();
       Document doc = (Document)db.getCustCollection().find(Filters.eq("acc.Email", email)).first();
       if(doc==null) { c = null ; return c ;}
       c = db.getGson().fromJson(doc.toJson(), Customer.class);
       db.CloseConnection();
        return c;
 
    }
        
      public void updateCustomer() {
          
        Database db = new Database();
        Document doc = Document.parse(db.getGson().toJson(this));
        db.getCustCollection().replaceOne(Filters.eq("_id", this.getId()), doc);
        System.out.println("Customer Updated");
        db.CloseConnection();

    }
      
          public void deleteCustomer() {
            
        Database db = new Database();
        db.getCustCollection().deleteOne(Filters.eq("_id", this.getId()));
        System.out.println("Customer Deleted");
        db.CloseConnection();
        
   }
    
    
    // Payment Methods
    public void walletDeduction(double amount){
        wallet = wallet - amount;
    }
    public void addCredit(double amount){
        wallet = wallet + amount;
    }
    
}
