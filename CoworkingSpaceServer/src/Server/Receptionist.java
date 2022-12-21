package Server;


import Interface.IReceptionist;
import RMI.Account;
import java.util.Objects;
import org.bson.Document;
import com.mongodb.client.model.Filters;


public class Receptionist implements IReceptionist {
    
    private int _id;
    private String SSN;
    private String Address;
    private double salary;
    private Account acc;

    public Receptionist() {
        
        this._id = 0;
        this.SSN = "";
        this.Address = "";
        this.salary = 0;
        this.acc = null;
        
    }


    public Receptionist(int id, String SSN, String Address, double salary, Account acc) {
        
        this._id = id;
        this.SSN = SSN;
        this.Address = Address;
        this.salary = salary;
        this.acc = acc;
        
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
        final Receptionist other = (Receptionist) obj;
        if (this._id != other._id) {
            return false;
        }
        if (Double.doubleToLongBits(this.salary) != Double.doubleToLongBits(other.salary)) {
            return false;
        }
        if (!Objects.equals(this.SSN, other.SSN)) {
            return false;
        }
        if (!Objects.equals(this.Address, other.Address)) {
            return false;
        }
        return Objects.equals(this.acc, other.acc);
    }

   
    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public String getSSN() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Account getAcc() {
        return acc;
    }

    public void setAcc(Account acc) {
        this.acc = acc;
    }

    @Override
    public String toString() {
        return "Receptionist{" + "_id=" + _id + ", SSN=" + SSN + ", Address=" + Address + ", salary=" + salary + ", acc=" + acc + '}';
    }
    
    
        public void InsertReceptionist() {
            
        Database db = new Database();
        db.getRecepCollection().insertOne(Document.parse(db.getGson().toJson(this)));
        System.out.println("Receptionist inserted.");
        db.CloseConnection();
    }
       
        public Receptionist getReceptbByEmail(String email) {
            
        Database db = new Database();
        Receptionist c = new Receptionist();
       Document doc = (Document)db.getRecepCollection().find(Filters.eq("acc.Email", email)).first();
       if(doc==null) { c = null ; return c ;}
       c = db.getGson().fromJson(doc.toJson(), Receptionist.class);
       db.CloseConnection();
        return c;
    }
        
      
       public void updateReceptionist() {
           
        Database db = new Database();
        Document doc = Document.parse(db.getGson().toJson(this));
        db.getRecepCollection().replaceOne(Filters.eq("_id", this.getId()), doc);
        System.out.println("Receptionist Updated");
         db.CloseConnection();

    }
    
      public void deleteReceptionist() {
          
        Database db = new Database();
        db.getRecepCollection().deleteOne(Filters.eq("_id", this.getId()));
        System.out.println("Receptionist Deleted");
        db.CloseConnection();
        
   }
    
    
}
