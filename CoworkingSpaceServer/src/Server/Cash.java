package Server;

import Interface.PaymentMethod;


public class Cash implements PaymentMethod{
   private float CashBack;

    public Cash() {
    }

    public Cash(float CashBack) {
        this.CashBack = CashBack;
    }

    public void setCashBack(float CashBack) {
        this.CashBack = CashBack;
    }

    public float getCashBack() {
        return CashBack;
    }
     
    @Override
    public void MakePayment(float amount) {
         if(amount>=100){
              CashBack=(float) (amount*.1);
              System.out.println("you have paid amount :"+amount);
              System.out.println("your cash back amount :"+ CashBack);
         }
         else System.out.println("you have paid amount :"+amount);
    }
   
}