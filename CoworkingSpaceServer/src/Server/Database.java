package Server;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
     
   private MongoClient conn;
   private MongoCollection AdminCollection;
   private MongoCollection RecepCollection;
   private MongoCollection CustCollection;
   private MongoCollection Reservation;
   private MongoCollection Workspace;
   private MongoDatabase workspaceDB ;
   private Gson gson;

    public Gson getGson() {
        return gson;
    }

    public Database() {
        
        try {         
            Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
            mongoLogger.setLevel(Level.SEVERE);
            this.conn = new MongoClient("localhost", 27017);

            this.workspaceDB = conn.getDatabase("CWS");
            this.AdminCollection = workspaceDB.getCollection("Admin");
            this.RecepCollection = workspaceDB.getCollection("Receptionist");
            this.CustCollection = workspaceDB.getCollection("Customer");
            this.Reservation = workspaceDB.getCollection("ReservationMapper");
            this.Workspace = workspaceDB.getCollection("WorkSpace");

            this.gson = new Gson();
        }
        catch(MongoException e){         
            System.out.println("Server Error" + e);  
        }            
    }

    public MongoCollection getAdminCollection() {
        return AdminCollection;
    }

    public MongoCollection getRecepCollection() {
        return RecepCollection;
    }

    public MongoCollection getCustCollection() {
        return CustCollection;
    }
    
    public MongoCollection getReservCollection(){
        return Reservation;
    }
    
    public MongoCollection getWorkSpaceCollection(){
        return Workspace;
    }
    
    public MongoClient getConn() {
        return conn;
    }
 
    
    public void CloseConnection(){     
        conn.close();
    }
    
    public void getDatabaseNames() {           
        List <String> db = conn.getDatabaseNames();
        System.out.println(db);        
    }

    public void DropDatabase(){   
        workspaceDB.drop();
        System.out.println("Database droped");
    }
   
      
}
