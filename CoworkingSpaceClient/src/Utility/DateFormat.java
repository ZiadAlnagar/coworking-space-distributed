package Utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {
    public static String getDateNow() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }
}