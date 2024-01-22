package com.planc.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CommonUtils {
	
	private static SimpleDateFormat defSdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 
	
	private CommonUtils() {
	}
	
	public static Date timeStampToDate(Timestamp time) {
		if(time==null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time.getTime());
		return cal.getTime();
	}
	
	public static String dateToString(Date date) {
		return dateToString(defSdf, date);
	}
	
	public static String dateToString(SimpleDateFormat sdf, Date date) {
		if(date==null) {
			return null;
		}
		sdf = ((sdf==null)?defSdf:sdf);
		return sdf.format(date);
	}
	
	public static Timestamp findCurrentTimestamp() {
		return new Timestamp(new Date().getTime());
	}
	
}

