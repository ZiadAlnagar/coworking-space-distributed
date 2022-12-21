package Cafeteria;

import Interface.IOrder;
import Utility.DateFormat;
import Utility.Globals;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static Utility.Globals.OrderStatus.INPROGRESS;

public class Order implements IOrder {
    private int id = 0;
    private String dateTime;
    private Globals.OrderStatus status;
    private int totalPrice = 0;
    Cafeteria cafeteria = Cafeteria.getInstance();
    private ArrayList<int[]> orderItems = new ArrayList<int[]>();

    // GUI Components Initialization
    JLayeredPane lPane = new JLayeredPane();
    JFrame frame = new JFrame("Cafeteria");
    JPanel containerPanel = new JPanel();
    JPanel itemPanel = new JPanel(new BorderLayout());
    //JScrollPane scroll = new JScrollPane(itemPanel);
    GridLayout gridLayout = new GridLayout(2,1, 50, 10);
    JPanel confirmPanel = new JPanel();
    JPanel submitPanel = new JPanel();
    JLabel totalPriceLabel = new JLabel();
    JLabel selectedItemStatusLabel = new JLabel();
    JButton orderBtn = new JButton("Order");
    JButton cancelBtn = new JButton("Cancel");
    JLabel orderItemsLabel = new JLabel();
    JButton orderYesBtn = new JButton("Yes");
    JButton orderNoBtn = new JButton("No");

    public Order() {
        id = ++id;
        dateTime = DateFormat.getDateNow();
        status = INPROGRESS;
    }

    private void loadGUI() {
        // Frame
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1280, 720));
        //frame.setSize(1280, 720);
        frame.setLayout(new GridLayout(1,1));
        frame.add(lPane, BorderLayout.CENTER);
        // Scroll
        //scroll.setSize(new Dimension(500, 250));
        //scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        // LayeredPanel
        //lPane.setBounds(0, 0, 1280, 720);
        lPane.add(containerPanel, JLayeredPane.DEFAULT_LAYER);
        lPane.add(confirmPanel, JLayeredPane.POPUP_LAYER);
        // Panels
        // Order Panel
        GridLayout grid = new GridLayout(cafeteria.getItemList().size(),1, 1, 10);
        itemPanel.setLayout(grid);
        itemPanel.setBounds(0, 0, 1000, 600);
        itemPanel.setOpaque(true);
        // OrderConfirmation Panel
        confirmPanel.setLayout(gridLayout);
        //confirmPanel.setPreferredSize(frame.getSize());
        confirmPanel.setBounds(0, 0, 600, 400);
        confirmPanel.setOpaque(true);
        confirmPanel.setVisible(false);
        // Container Panel
        containerPanel.setLayout(gridLayout);
        containerPanel.setBounds(0, 0, 1000, 600);
        containerPanel.setOpaque(true);
        containerPanel.add(itemPanel, BorderLayout.NORTH);
        containerPanel.add(submitPanel, BorderLayout.SOUTH);
        cancelBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancelOrder();
            }
        });
        // ConfirmOrder Panel Components
        // -> First Panel
        JPanel orderPanel = new JPanel();
        orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));
        orderPanel.add(new JLabel("Confirm order?"));
        orderPanel.add(orderItemsLabel);
        confirmPanel.add(orderPanel);
        // -> Second Panel
        JPanel confirmOptions = new JPanel();
        confirmOptions.setLayout(new BoxLayout(confirmOptions, BoxLayout.X_AXIS));
        confirmOptions.setSize(50, 10);
        confirmOptions.add(orderYesBtn);
        confirmOptions.add(orderNoBtn);
        confirmPanel.add(confirmOptions);
        // Submit Panel Components
        submitPanel.setLayout(new BoxLayout(submitPanel, BoxLayout.Y_AXIS));
        // -> First Panel
        JPanel orderDetailsPanel = new JPanel();
        orderDetailsPanel.setLayout(new BoxLayout(orderDetailsPanel, BoxLayout.Y_AXIS));
        orderDetailsPanel.add(selectedItemStatusLabel);
        orderDetailsPanel.add(totalPriceLabel);
        submitPanel.add(orderDetailsPanel);
        // -> Second Panel
        JPanel orderSubmitPanel = new JPanel();
        orderSubmitPanel.setLayout(new BoxLayout(orderSubmitPanel, BoxLayout.X_AXIS));
        orderSubmitPanel.add(orderBtn);
        orderSubmitPanel.add(cancelBtn);
        submitPanel.add(orderSubmitPanel);
        // Any
        orderBtn.setEnabled(false);
        // EventListeners
        orderBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                confirmOrder(orderItems);
            }
        });
    }

    @Override
    public void makeOrder() {
        loadGUI();
        loadItems();
        frame.pack();
        frame.setVisible(true);
    }

    public void loadItems() {
        int i = 0;
        for (Item item : cafeteria.getItemList()) {
            String itemName = item.getName();
            int itemQuantity = item.getQuantity();
            double itemPrice = item.getPrice();
            JLabel nameLabel = new JLabel(itemName);
            JLabel priceLabel = new JLabel("$ " + itemPrice);
            SpinnerModel quantityModel = new SpinnerNumberModel(1, 0, itemQuantity, 1.0);
            JSpinner quantitySpinner = new JSpinner(quantityModel);
            quantitySpinner.setName("quantity" + i);
            JButton addBtn = new JButton("Add");
            addBtn.setName("add" + i);
            JButton removeBtn = new JButton("-");
            removeBtn.setName("remove" + i);
            if (itemQuantity == 0) addBtn.setEnabled(false);
            int finalI = i;
            addBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Double tempQ = (Double) quantitySpinner.getValue();
                    int currQuantity = tempQ.intValue();
                    addItem(finalI, currQuantity);
                    calcTotalPrice();
                    isOrderable();
                }
            });
            removeBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    removeItem(finalI);
                    calcTotalPrice();
                    isOrderable();
                }
            });
            i++;
            itemPanel.add(nameLabel);
            itemPanel.add(priceLabel);
            itemPanel.add(quantitySpinner);
            itemPanel.add(addBtn);
            itemPanel.add(removeBtn);
        }
    }

    @Override
    public void addItem(int id, int quantity) {
        boolean found = false;
        int i = 0;
        int[] tempItem = null;
        if (!orderItems.isEmpty()) {
            for (int[] ii : orderItems) {
                if (orderItems.get(i)[0] == id) {
                    found = true;
                    tempItem = ii;
                    break;
                }
                i++;
            }
        }
        if(found) {
            orderItems.set(i, new int[] {tempItem[0], tempItem[1] + quantity});
            selectedItemStatusLabel.setText("Quantity Added");
        } else {
            orderItems.add(new int[]{id, quantity});
            selectedItemStatusLabel.setText("Item Added");
        }
    }

    @Override
    public void removeItem(int id) {
        int i = 0;
        boolean found = false;
        if (!orderItems.isEmpty()) {
            for (int[] ii : orderItems) {
                if (orderItems.get(i)[0] == id) {
                    found = true;
                    break;
                }
                i++;
            }
            if(found) {
                orderItems.remove(i);
                submitPanel.setEnabled(false);
                selectedItemStatusLabel.setText("Item Removed");
            } else selectedItemStatusLabel.setText("");
        } else selectedItemStatusLabel.setText("");
    }

    @Override
    public void cancelOrder() {
        // Redirect user to homepage
    }

    private void calcTotalPrice() {
        int ii = 0;
        double totalPrice = 0;
        for(int[] i : orderItems) {
            int itemIndex = orderItems.get(ii)[0];
            int itemQuantity = orderItems.get(ii)[1];
            double price = cafeteria.getItemList().get(itemIndex).getPrice();
            totalPrice += price * itemQuantity;
            ii++;
        }
        totalPriceLabel.setText("<html><body><pre>$ " + Double.toString(totalPrice) + "</pre></body></html>");
    }

    private void isOrderable() {
        if(!orderItems.isEmpty()) {
            orderBtn.setEnabled(true);
        } else orderBtn.setEnabled(false);
    }

    @Override
    public void confirmOrder(ArrayList<int[]> list) {
        confirmPanel.setVisible(true);
        String items = "<html><body><pre>Item         Quantity<br>" +
                "=====================<br>";
        for (int[] i : orderItems) {
                    items += cafeteria.getItemList().get(i[0]).getName() + "         " + i[1] + "<br>";
        }
        items += "Total price: " + totalPriceLabel.getText() + "</pre></body></html>";
        orderItemsLabel.setText(items);
        //orderItemsLabel.setSize(500, 600);
        orderYesBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        orderNoBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                confirmPanel.setVisible(false);
            }
        });
    }
}
