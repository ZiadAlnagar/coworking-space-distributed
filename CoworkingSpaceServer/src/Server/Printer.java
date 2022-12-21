package Server;

import Interface.ICustomer;
import Interface.IPrinter;
import Interface.IReceptionist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Printer implements IPrinter {
    private static Printer printer = null;
    JFrame frame = new JFrame("Print");
    JPanel containerPanel = new JPanel();
    JPanel customerPanel = new JPanel();
    JPanel receptionistPanel = new JPanel();
    JLabel printLocLabel = new JLabel("Enter file location:");
    JTextArea printLocText = new JTextArea();
    JButton printBtn = new JButton("Print");
    JLabel printStatus = new JLabel();
    JLabel listPrintLabel = new JLabel();

    public static Printer getInstance()
    {
        if (printer == null)
            printer = new Printer();

        return printer;
    }

    private void loadGUI() {
        // Frame
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1280, 720));
    }

    private void packGUI() {
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void sendPrintRequest(ICustomer c, IReceptionist r) {
        frame.add(containerPanel);
        loadGUI();
        int customerId = c.getId();
        int receptionistId = r.getId();
        String print = printLocText.getText();
        customerPanel.add(printLocLabel);
        customerPanel.add(printLocText);
        containerPanel.add(customerPanel);
        containerPanel.add(printBtn);
        packGUI();
        printBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //send to db here
                printStatus.setText("Print Request Sent");
            }
        });
    }

    @Override
    public void viewQueueList() {
        frame.add(receptionistPanel);
        receptionistPanel.add(listPrintLabel);
        String printRow = "<html><body><pre><br>";
        //foreach db row{
        //PrintRow += ;
        //}
        printRow += "</pre></body></html>";
        listPrintLabel.setText(printRow);
        listPrintLabel.setPreferredSize(new Dimension(800, 600));
        packGUI();
    }
}
