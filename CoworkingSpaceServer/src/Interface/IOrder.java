package Interface;

import java.util.ArrayList;

public interface IOrder {
    public void makeOrder();

    public void addItem(int index, int quantity);

    public void removeItem(int index);

    public void cancelOrder();

    public void confirmOrder(ArrayList<int[]> list);
}
