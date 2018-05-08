package cn.cg.tool;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ChenGeng on 2017/12/6.
 */
public class TimeFormatter {

    /**
     * 获取当前时间和日期的字符串
     * @param withSymbol 是否带-和:
     * @return
     */
    public static String getDateTimeString(boolean withSymbol){
        Date date = new Date();
        SimpleDateFormat format = dateFormat();
        String string = format.format(date);
        if(!withSymbol){
            string = string.replaceAll("-","");
            string = string.replace(":","");
        }
        return string;
    }

    /**
     * 获取当前日期字符串
     * @param withSymbol 是否带-
     * @return
     */
    public static String getDateString(boolean withSymbol){
        String dateTimeString = getDateTimeString(withSymbol);
        return dateTimeString.substring(0,dateTimeString.indexOf(" "));
    }

    /**
     * 获取当前时间字符串
     * @param withSymbol 是否带:
     * @return
     */
    public static String getTimeString(boolean withSymbol){
        String dateTimeString = getDateTimeString(withSymbol);
        return dateTimeString.substring(dateTimeString.indexOf(" ")+1);
    }

    public static Date getDate(String dateString){
        int year = Integer.valueOf(dateString.substring(0,4));
        int month = Integer.valueOf(dateString.substring(5,7))-1;
        int date = Integer.valueOf(dateString.substring(8));
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, date);
        return calendar.getTime();
    }

    private static SimpleDateFormat dateFormat(){
        return new SimpleDateFormat ("YYYY-MM-dd HH:mm:ss");
    }

}
