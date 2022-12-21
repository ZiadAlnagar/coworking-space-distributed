package Server;


import com.mongodb.client.model.Filters;
import org.bson.Document;


public class ReservationMapper {
    private Reservation reservation;
    private Database db = new Database();

    public ReservationMapper(Reservation reservation) {
        this.reservation = reservation;
    }

    public Object loadReservation() {
        Object obj = db.getReservCollection().find(Filters.eq("_id", reservation.getId())).first();
        db.CloseConnection();
        return obj;
    }
    
    public void insertReservation() {
        db.getReservCollection().insertOne(Document.parse(db.getGson().toJson(reservation)));
        System.out.println("Reservation inserted.");
        db.CloseConnection();
    }
    
    public void updateReservation() {
        Document doc = Document.parse(db.getGson().toJson(reservation));
        db.getReservCollection().replaceOne(Filters.eq("_id", reservation.getId()), doc);
        System.out.println("Reservation Updated.");
        db.CloseConnection();
    }
    
    public void deleteReservation() {
        db.getReservCollection().deleteOne(Filters.eq("_id", reservation.getId()));
        System.out.println("Reservation Deleted.");
        db.CloseConnection();
    }
}
