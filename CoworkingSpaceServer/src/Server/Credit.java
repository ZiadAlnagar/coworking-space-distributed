package Server;

import Interface.PaymentMethod;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class Credit implements PaymentMethod{
    private final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    
    private String CreditCardNumber;
    private String CreditCardName;
    private String CreditCardCompany;
    private String CreditCardExpDate;
    private String CreditCVV;

    public Credit() {
    }

    public Credit(String CreditCardNumber, String CreditCardName, String CreditCardCompany, String CreditCardExpDate, String CreditCVV) {
        this.CreditCardNumber = CreditCardNumber;
        this.CreditCardName = CreditCardName;
        this.CreditCardCompany = CreditCardCompany;
        this.CreditCardExpDate = CreditCardExpDate;
        this.CreditCVV = CreditCVV;
    }

    public void setCreditCardNumber(String CreditCardNumber) {
        this.CreditCardNumber = CreditCardNumber;
    }

    public void setCreditCardName(String CreditCardName) {
        this.CreditCardName = CreditCardName;
    }

    public void setCreditCardCompany(String CreditCardCompany) {
        this.CreditCardCompany = CreditCardCompany;
    }

    public void setCreditCardExpDate(String CreditCardExpDate) {
        this.CreditCardExpDate = CreditCardExpDate;
    }

    public void setCreditCVV(String CreditCVV) {
        this.CreditCVV = CreditCVV;
    }

    public BufferedReader getREADER() {
        return READER;
    }

    public String getCreditCardNumber() {
        return CreditCardNumber;
    }

    public String getCreditCardName() {
        return CreditCardName;
    }

    public String getCreditCardCompany() {
        return CreditCardCompany;
    }

    public String getCreditCardExpDate() {
        return CreditCardExpDate;
    }

    public String getCreditCVV() {
        return CreditCVV;
    }

   

    @Override
    public void MakePayment(float amount) {
        try{
            System.out.println("Enter the card number :");
            CreditCardNumber=READER.readLine();
            System.out.print("Enter the card expiration date 'mm/yy': ");
            CreditCardExpDate = READER.readLine();
            System.out.print("Enter the card Name :");
            CreditCardName = READER.readLine();
            System.out.print("Enter the card Company ");
            CreditCardCompany = READER.readLine();
            System.out.print("Enter the card CVV : ");
            CreditCVV = READER.readLine();
            System.out.println("==========================");
            System.out.println("Form Card "+CreditCardNumber +" we have recived amount :"+amount);
        }
        catch(IOException ex){
            ex.printStackTrace();
       
        }
        
    }
}