package com.pland.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

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
	
	public static <T> List<T> iterableToList(Iterable<T> iterables) {
		Iterator<T> iterators = ((iterables!=null)?iterables.iterator():null);
		if(iterators==null) {
			return null;
		}
		List<T> rtns = new ArrayList<T>();
		while(iterators.hasNext()) {
			T obj = iterators.next();
			if(obj==null) {
				continue;
			}
			rtns.add(obj);
		}
		return ((rtns!=null && rtns.size()>0)?rtns:null);
	}
	
	public static <T> T optionalToObj(Optional<T> optional) {
		return ((optional.isPresent())?optional.get():null);
	}
	
}
