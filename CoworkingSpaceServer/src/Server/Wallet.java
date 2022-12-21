package Server;

import Interface.PaymentMethod;


public class Wallet implements PaymentMethod{
  private Customer Cust;

    public Wallet() {
    }

    public Wallet(Customer Cust) {
        this.Cust = Cust;
    }

    public void setCust(Customer Cust) {
        this.Cust = Cust;
    }

    public Customer getCust() {
        return Cust;
    }
    

    @Override
    public void MakePayment(float amount) {
        Cust.walletDeduction(amount);
        System.out.println("The customer wallet balance :"+Cust.getWallet());
        if(amount>=100){
             float CashBack=(float) (amount*.1);
              System.out.println("you have paid amount :"+amount);
              System.out.println("your cash back amount :"+ CashBack);
              Cust.addCredit(CashBack);
              System.out.println("your new wallet balance"+Cust.getWallet());
         }
         else System.out.println("you have paid amount :"+amount);
    }

}