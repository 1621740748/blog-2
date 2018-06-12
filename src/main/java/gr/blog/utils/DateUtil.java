package gr.blog.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static final String DATE_FORMAT_1 = "yyyyMMddhhmmss";

    public static  String format(Date date, String partton){
        SimpleDateFormat format = new SimpleDateFormat(partton);
        return format.format(date);
    }
}
