
package Server;


public class RoomService {
    private String name; // WIFI, BOARD, PROJECTOR, COMPUTER
    private float price;

    public RoomService(String name, float price) {
        this.name = name;
        this.price = price;
    }
    
    // Setters & Getters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }    
}
