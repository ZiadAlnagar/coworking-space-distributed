package Cafeteria;

import java.util.ArrayList;

public class Cafeteria {
    private static Cafeteria cafeteria = null;
    private String name;
    private String ownerName;
    private int level;
    private final ArrayList<Item> itemList = new ArrayList<>();
    private Cafeteria()
    {
        name = "Caf Zone";
        ownerName = "Owner";
        level = 1;
        setItemList();
    }

    public static Cafeteria getInstance()
    {
        if (cafeteria == null)
            cafeteria = new Cafeteria();

        return cafeteria;
    }

    private void setItemList() {
        itemList.add(new Item("Oreo", 200, 5));
        itemList.add(new Item("Chips Salt", 100, 7));
        itemList.add(new Item("Chips Cheese", 75, 7));
        itemList.add(new Item("Doritos Nacho Cheese", 50, 10));
        itemList.add(new Item("Wafers", 100, 3));
        itemList.add(new Item("Nescafe", 750, 15));
        itemList.add(new Item("Tea", 100000, 7));
        itemList.add(new Item("Pepsi", 300, 7));
        itemList.add(new Item("Juhayna Mix Strawberry Milk", 250, 5));
        itemList.add(new Item("Juhayna Mix Chocolate Milk", 250, 5));
    }

    public ArrayList<Item> getItemList() {
        return itemList;
    }

    public void setQuantity(int i, int quantity) {
        Item item = itemList.get(i);
        item.setQuantity(quantity);
        itemList.set(i, item);
    }
}
