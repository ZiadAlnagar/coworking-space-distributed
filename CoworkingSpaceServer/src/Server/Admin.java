package Server;


import RMI.Account;
import java.util.Objects;
import com.mongodb.client.model.Filters;
import org.bson.Document;


public class Admin extends Observer {
    
    private int _id;
    private double salary;
    private int controlDegree;
    private Account acc;

      

     public Admin(int id, double salary, int controlDegree, Account acc) {
        this._id = id;
        this.salary = salary;
        this.controlDegree = controlDegree;
        this.acc = acc;  
        
    }

    public Admin() {
        
        this._id = 0;
        this.salary = 0;
        this.controlDegree = 0;
        this.acc = null;  
    }

    
    public Admin(Feedback feedback){
        
      this.feedback = feedback;
      this.feedback.attach(this);
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
        final Admin other = (Admin) obj;
        if (this._id != other._id) {
            return false;
        }
        if (Double.doubleToLongBits(this.salary) != Double.doubleToLongBits(other.salary)) {
            return false;
        }
        if (this.controlDegree != other.controlDegree) {
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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getControlDegree() {
        return controlDegree;
    }

    public void setControlDegree(int controlDegree) {
        this.controlDegree = controlDegree;
    }

    public Account getAcc() {
        return acc;
    }

    public void setAcc(Account acc) {
        this.acc = acc;
    }
    
   
    public void addAdmin(Admin a){
       
    }
     
   public void updateSalary(int ReceptionistID, int NewSalary){
        
    }

    @Override
    public String toString() {
        return "Admin{" + "id=" + _id + ", salary=" + salary + ", controlDegree=" + controlDegree + ", acc=" + acc + '}';
    }
   
      public void InsertAdmin() {

       Database db = new Database();
       db.getAdminCollection().insertOne(Document.parse(db.getGson().toJson(this)));
        System.out.println("Admin inserted.");
       db.CloseConnection();

    }
      
      public Admin getAdmintbByEmail(String email) {
          
       Admin a = new Admin();
       Database db = new Database();
       Document doc = (Document)db.getAdminCollection().find(Filters.eq("acc.Email", email)).first();
       if(doc==null) { a = null ; return a ;}
       a = db.getGson().fromJson(doc.toJson(), Admin.class);
       db.CloseConnection();
        return a;
       
    }
      
      
     public void updateAdmin() {
         
        Database db = new Database();
        Document doc = Document.parse(db.getGson().toJson(this));
        db.getAdminCollection().replaceOne(Filters.eq("_id", this.getId()), doc);
        System.out.println("Admin Updated");
        db.CloseConnection();

    }
     
    
       public void deleteAdmin() {
           
        Database db = new Database();
        db.getAdminCollection().deleteOne(Filters.eq("_id", this.getId()));
        System.out.println("Admin Deleted");
        db.CloseConnection();
   }
       
         @Override
    public void update() {
        System.out.println((feedback.getFeedback())); 
    }
    
       
}
