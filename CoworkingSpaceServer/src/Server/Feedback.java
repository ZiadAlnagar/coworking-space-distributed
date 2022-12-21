package Server;

import java.util.ArrayList;
import java.util.List;


public class Feedback  
{
    private int id;
    private String title;
    private String description;
    private String dateAndTime;
    private String FeedbackType; //state
    String nul="";
    private List<Observer> observers = new ArrayList<Observer>();
    
    
    public String getFeedback() {
        System.out.println("FeedBack ID :"+id);
        System.out.println("FeedBack Title :"+title);
        System.out.println("FeedBack Descrption :"+description);
        System.out.println("FeedBack Date and Time: "+dateAndTime);
        System.out.println("FeedBack Feedback Type :"+FeedbackType);
        return nul;
   }

   public void setFeedBack(int ID,String Title,String Description,String Date,String feedbackType) {
      this.id=ID;
      this.title=Title;
      this.description=Description;
      this.dateAndTime=Date;
      this.FeedbackType=feedbackType;
      notifyAllObservers();
   }

   public void attach(Observer observer){
      observers.add(observer);		
   }

   public void notifyAllObservers(){
      for (Observer observer : observers) {
         observer.update();
      }
   } 	
    
}
