package Interface;

public interface IPrinter {
    public void sendPrintRequest(ICustomer c, IReceptionist r);
    public void viewQueueList();
}
