package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	private static String format = "dd/MM/yyyy";

	public static String getDate() {
		 Date date = Calendar.getInstance().getTime();
         DateFormat dateFormat = new SimpleDateFormat(DateUtil.format);
         
         return dateFormat.format(date); 
	}
	
	public static boolean validateDate(String date) {
		DateFormat sdf = new SimpleDateFormat(DateUtil.format);
        sdf.setLenient(false);
        
        try {
            sdf.parse(date);
        } catch (ParseException e) {
            return false;
        }
        
        return true;
	}
}
