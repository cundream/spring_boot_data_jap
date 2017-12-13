package com.lc.crm.util;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;


/**
 * 时间处理方面的工具。
 * 
 * @author Administrator
 *
 */
public final class TimeUtil {

	/**
	 * 把UNIX时间戳转换为类似2009-11-09 11:31:26这种格式的java字符串。
	 * 
	 * @param timestampString String类型的Unix时间戳
	 * @return
	 */
	public static String timeStamp2DateStr(String timestampString) {
		Long timestamp = Long.parseLong(timestampString) * 1000;
		return new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date(timestamp));
	}

	/**
	 * 把UNIX时间戳转换为类似2009-11-09 11:31:26这种格式的java字符串。
	 *
	 * @param timestamp1 long类型的Unix时间戳
	 * @return
	 */
	public static String timeStamp2Str(long timestamp1) {
		Long timestamp = timestamp1 * 1000;
		return new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date(timestamp));
	}
	
	/**
	 * 把UNIX时间戳转换为对应的Java Date对象
	 * 
	 * @param timestampString 实际为long类型的值的UNIX时间戳
	 * @return
	 */
	public static Date timeStamp2Date(String timestampString) {
		Long timestamp = Long.parseLong(timestampString) * 1000L;
		return new Date(timestamp);
	}
	
	/**
	 * 把UNIX时间戳转换为对应的Java Date对象
	 * 
	 * @param timestamp 实际为long类型的值的UNIX时间戳
	 * @return
	 */
	public static Date timeStamp2Date(long timestamp) {
		Long timeStamp = timestamp * 1000L;
		return new Date(timeStamp);
	}
	
	public static long dateStrToSec(String dateStr) throws ParseException {
		return (new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr)).getTime()/1000;
	}

	/**
	 * 获得获得系统的UNIX时间戳的值
	 * 
	 * @return
	 */
	public static long getUnixTimestamp() {
		return System.currentTimeMillis()/1000L;
	}
	
	public static String getTimeLong(long time) {
		String s = "%d天%d小时%d分%d秒";
		int day = (int)(time / (24*3600*1000l));
		int hour = (int)((time % (24*3600*1000l)) / (3600*1000));
		int min = (int)(((time % (24*3600*1000l)) % (3600*1000)) / (60*1000));
		int sec = (int)((((time % (24*3600*1000l)) % (3600*1000)) % (60*1000)) / (1000));
		
		return String.format(s, day, hour, min, sec);
	}
	
//	public static void main(String[] args) throws InterruptedException {
//		System.out.println(4&7);
//		
////		long now = getUnixTimestamp();
//		long now = 1477818900;
//		System.out.println("now="+(int)now);
//		System.out.println("now="+now);
//		System.out.println("now="+new java.util.Date(now*1000L));
//		
//		System.out.println("now="+timeStamp2DateStr(String.valueOf(now)));
//		
//		Thread.sleep(3500);
//		long now1 = getUnixTimestamp();
//		System.out.println("now1="+now1);
//		//误差不超过1秒
//		System.out.println("now1-now="+(now1-now));
//	}

	public static long getTodayStartSecond(){
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.HOUR_OF_DAY,0);
		ca.set(Calendar.MINUTE,0);
		ca.set(Calendar.SECOND,0);
		System.out.println(ca.getTime());
		return ca.getTimeInMillis()/1000;
	}

	public static long getTodayEndSecond(){
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.HOUR_OF_DAY,23);
		ca.set(Calendar.MINUTE,59);
		ca.set(Calendar.SECOND,59);
		System.out.println(ca.getTime());
		return ca.getTimeInMillis()/1000;
	}
	
	/**
	 * 获取一天的开始时间0点整
	 * @param timeStart
	 * @return
	 */
	public static Date getStartTime(Date timeStart) {
		Calendar ca = Calendar.getInstance();
		if (timeStart != null) {
			ca.setTime(timeStart);
		}
		ca.set(Calendar.HOUR_OF_DAY, 0);
		ca.set(Calendar.MINUTE, 0);
		ca.set(Calendar.SECOND, 0);
		return ca.getTime();
	}

	/**
	 * 获取一天的开始时间0点整
	 * @param timeStart
	 * @return
	 */
	public static Date getOnDayStartTime(Date timeStart) {
		Calendar ca = Calendar.getInstance();
		if (timeStart != null) {
			ca.setTime(timeStart);
		}
		ca.add(Calendar.DAY_OF_YEAR,-1);
		ca.set(Calendar.HOUR_OF_DAY, 0);
		ca.set(Calendar.MINUTE, 0);
		ca.set(Calendar.SECOND, 0);
		return ca.getTime();
	}

	/**
	 * 获取前一周的时间
	 * @param timeStart
	 * @return
	 */
	public static Date getOnWeekStartTime(Date timeStart) {
		Calendar ca = Calendar.getInstance();
		if (timeStart != null) {
			ca.setTime(timeStart);
		}

		ca.add(Calendar.DATE, -6);
		ca.set(Calendar.MINUTE, 0);
		ca.set(Calendar.SECOND, 0);
		return ca.getTime();
	}
	/**
	 * 获取一天的开始结束0点整
	 * @param timeEnd
	 * @return
	 */
	public static Date getEndTime(Date timeEnd) {
		Calendar ca = Calendar.getInstance();
		if (timeEnd != null) {
			ca.setTime(timeEnd);
		}
		ca.set(Calendar.HOUR_OF_DAY, 23);
		ca.set(Calendar.MINUTE, 59);
		ca.set(Calendar.SECOND, 59);
		return ca.getTime();
	}

	/**
	 * 获取当前时间的下一个月
	 * @param timeEnd
	 * @return
	 */
	public static Date getNextMonthTime(Date timeEnd) {
		Calendar ca = Calendar.getInstance();
		if (timeEnd != null) {
			ca.setTime(timeEnd);
		}
		ca.add(Calendar.MONTH, 1);
		return ca.getTime();
	}

	/**
	 * @param date
	 * @param minute
	 * @return
	 */
	public static Date getTime(Date date,int minute) {
		Calendar ca = Calendar.getInstance();
		if (date != null) {
			ca.setTime(date);
		}
		ca.add(Calendar.MINUTE, minute);
		return ca.getTime();
	}

	/**
	 * 15分钟的日期时间转换为时间戳格式
	 *
	 * @param date
	 * @return
	 */
	public static Long formatDateStamp(Date date){
		if (0 <= date.getMinutes() && date.getMinutes() < 15) {
			date.setMinutes(0);
		} else if (15 <= date.getMinutes() && date.getMinutes() < 30) {
			date.setMinutes(15);
		} else if (30 <= date.getMinutes() && date.getMinutes() < 45) {
			date.setMinutes(30);
		} else if (45 <= date.getMinutes() && date.getMinutes() < 60) {
			date.setMinutes(45);
		}
		return  date.getTime();
	}

	/**
	 * 获取上个月月初
	 * @param timeEnd
	 * @return
	 */
	public static Date getLastMonth(Date timeEnd) {
		Calendar ca = Calendar.getInstance();
		if (timeEnd != null) {
			ca.setTime(timeEnd);
		}
		ca.add(Calendar.MONTH,-1);
		ca.set(Calendar.DAY_OF_MONTH, 1);
		ca.set(Calendar.HOUR_OF_DAY, 0);
		ca.set(Calendar.MINUTE, 0);
		ca.set(Calendar.SECOND, 0);
		return ca.getTime();
	}

//	public static void main(String[] args){
//		System.out.println(getLastMonth(new Date()));
//	}
}
