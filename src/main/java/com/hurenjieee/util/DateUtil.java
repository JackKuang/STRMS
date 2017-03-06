package com.hurenjieee.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	/**
	 * 本日日期 YYYY-MM-DD
	 * 
	 * @return
	 */
	public static String getToday() {
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = new java.util.Date();
		return sFormat.format(date);
	}

	/**
	 * 将字符串类型的日期转换成 YYYY-MM-dd
	 * 
	 * @return
	 */

	public static String stringTocalendar(String s1) {
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyyMMdd");
		try {
			Date odate = sFormat.parse(s1);
			sFormat = new SimpleDateFormat("yyyy-MM-dd");
			s1 = sFormat.format(odate);
		} catch (Exception e) {
			return "";
		}
		return s1;
	}

	/**
	 * 将字符串类型的时间转换成 hh:mm:ss
	 * 
	 * @return
	 */

	public static String stringTotime(String s1) {
		SimpleDateFormat sFormat = new SimpleDateFormat("HHmmss");
		try {
			Date odate = sFormat.parse(s1);
			sFormat = new SimpleDateFormat("HH:mm:ss");
			s1 = sFormat.format(odate);
		} catch (Exception e) {
			return "";
		}
		return s1;
	}

	/**
	 * 自定义格式输出日期 yyyyMMdd
	 */
	public static String getToday(String sFormatStr) {
		SimpleDateFormat sFormat = new SimpleDateFormat(sFormatStr);
		java.util.Date date = new java.util.Date();
		return sFormat.format(date);
	}

	public static String getToday4LocaleDate() {
		Date date = new Date();
		String dd = date.toLocaleString();
		dd = dd.substring(dd.indexOf(":") - 2);
		if (dd.charAt(0) == ' ') {
			dd = "0" + dd.substring(1);
		}
		return dd;
	}

	public static String getToday4LocaleTime() {
		java.util.Date date = new java.util.Date();
		String dd = date.toLocaleString();
		dd.split(":");
		return null;
	}

	/**
	 * getDate
	 * 
	 * @param pdate
	 * @param pattern
	 * @return
	 */
	public static final String getDate(Date pdate, String pattern) {
		if (pattern == null)
			pattern = "yyyyMMdd";
		return (new SimpleDateFormat(pattern)).format(pdate);

	}

	/**
	 * 用于日历方式的日期格式
	 * 
	 * @param date
	 * @return
	 */
	public static String getDate(java.util.Date date) {
		return getDate(date, "yyyy-MM-dd");
	}

	/**
	 * 用于日历方式的日期格式,精确至时分秒
	 */
	public static String getDateHms(java.util.Date date) {
		return getDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 根据输入格式,返回时间字符串
	 * 
	 * @return
	 */
	public static String getNowDateFormat(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = new Date();
		return sdf.format(date);

	}

	/**
	 * YYYY-MM
	 * 
	 * @return
	 */
	public static String getYearMonth() {
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM");
		java.util.Date date = new java.util.Date();
		return sFormat.format(date);
	}

	/**
	 * YYYY-MM 上一月
	 * 
	 * @param sDate
	 * @return
	 */
	public static String getPrevYearMonth(String sDate) {
		String sBack = "";
		if (sDate == null)
			return "";
		sDate = sDate.trim();
		String[] ss = sDate.split("-");
		if (ss.length != 2)
			return "";
		try {
			int iYear = new Integer(ss[0]).intValue();
			int iMonth = new Integer(ss[1]).intValue();
			if (iYear < 1900 || iYear > 2100)
				iYear = new Integer(getYear()).intValue();
			if (iMonth < 1 || iMonth > 12)
				iMonth = 1;
			if (iMonth == 1) { // 跨年
				iYear--;
				iMonth = 12;
			} else
				iMonth--;
			sBack = iYear + "-" + (iMonth < 10 ? "0" : "") + iMonth;
		} catch (Exception e) {
			return "";
		}
		return sBack;
	}

	/**
	 * YYYY-MM 下一月份
	 * 
	 * @param sDate
	 * @return
	 */
	public static String getNextYearMonth(String sDate) {
		String sBack = "";
		if (sDate == null)
			return "";
		sDate = sDate.trim();
		String[] ss = sDate.split("-");
		if (ss.length != 2)
			return "";
		try {
			int iYear = new Integer(ss[0]).intValue();
			int iMonth = new Integer(ss[1]).intValue();
			if (iYear < 1900 || iYear > 2100)
				iYear = new Integer(getYear()).intValue();
			if (iMonth < 1 || iMonth > 12)
				iMonth = 1;
			if (iMonth == 12) { // 跨年
				iYear++;
				iMonth = 1;
			} else
				iMonth++;
			sBack = iYear + "-" + (iMonth < 10 ? "0" : "") + iMonth;
		} catch (Exception e) {
			return "";
		}
		return sBack;
	}

	/**
	 * 日
	 * 
	 * @return
	 */
	public static String getDay() {
		SimpleDateFormat sFormat = new SimpleDateFormat("dd");
		java.util.Date date = new java.util.Date();
		return sFormat.format(date);
	}

	/**
	 * 本日日期和时间YYYY-MM-DD HH:MM:SS
	 * 
	 * @return
	 */
	public static String getTodayTime() {
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date date = new java.util.Date();
		return sFormat.format(date);
	}

	/**
	 * 将整型星期转为中文星期
	 */
	public static String chgWeekToGB(int iWeek) {
		switch (iWeek) {
		case 1:
			return "星期日";
		case 2:
			return "星期一";
		case 3:
			return "星期二";
		case 4:
			return "星期三";
		case 5:
			return "星期四";
		case 6:
			return "星期五";
		case 7:
			return "星期六";
		default:
			return null;
		}
	}

	/**
	 * 将整型星期转为英文星期
	 * 
	 * @param iWeek
	 * @return
	 */
	public static String chgWeekToEN(int iWeek) {
		int i = 0;
		switch (iWeek) {
		case 1:
			return (i == 0) ? "SUNDAY" : "SUN";
		case 2:
			return (i == 0) ? "MONDAY" : "MON";
		case 3:
			return (i == 0) ? "TUESDAY" : "TUE";
		case 4:
			return (i == 0) ? "WEDNESDAY" : "WED";
		case 5:
			return (i == 0) ? "THURSDAY" : "THU";
		case 6:
			return (i == 0) ? "FRIDAY" : "FRI";
		case 7:
			return (i == 0) ? "SATURDAY" : "SAT";
		default:
			return null;
		}
	}

	/**
	 * 将中文日期转为整型
	 * 
	 * @param iWeek
	 * @return
	 */
	public static int chgWeekToInt(String sWeek) {
		if (sWeek.equals("星期日"))
			return 1;
		else if (sWeek.equals("星期一"))
			return 2;
		else if (sWeek.equals("星期二"))
			return 3;
		else if (sWeek.equals("星期三"))
			return 4;
		else if (sWeek.equals("星期四"))
			return 5;
		else if (sWeek.equals("星期五"))
			return 6;
		else if (sWeek.equals("星期六"))
			return 7;
		else
			return 0;
	}

	/**
	 * 返回当前日期是星期几
	 * 
	 * @return
	 */
	public static int getDayOfWeek() {
		return getDayOfWeek(getToday());
	}

	/**
	 * 返回指定日期是星期几
	 * 
	 * @param sDate
	 * @return
	 */
	public static int getDayOfWeek(String sDate) {
		Calendar cal = getCalendar(sDate);
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 取得指定生产月份的开始日期
	 * 
	 * @param sMonth
	 */
	public static String getMonthBegin(String sMonth) {
		return getPrevYearMonth(sMonth) + "-26";
	}

	public static String getMonthBegin() {
		return getMonthBegin(getYearMonth());
	}

	/**
	 * 取得指定生产月份的结束日期
	 * 
	 * @param sMonth
	 */
	public static String getMonthEnd(String sMonth) {
		return sMonth + "-25";
	}

	public static String getMonthEnd() {
		return getMonthEnd(getYearMonth());
	}

	/**
	 * 从"YYYY-MM-DD"日期字符串得到日期类
	 * 
	 * @param sDate
	 * @return
	 */
	public static Date parseDate(String sDate) {
		if (sDate == null || sDate.trim().length() == 0)
			return null;
		try {
			Date d1 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
			return d1;
		} catch (Exception ex) {
			return null;
		}
	}

	public static Date getDate(String sDate, String sFormat) {
		try {
			Date d1 = new SimpleDateFormat(sFormat).parse(sDate);
			return d1;
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 形如YYYY-MM-DD的日期字符串转为 Calendar 型
	 */
	public static Calendar getCalendar(String sDate) {
		Date date = parseDate(sDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// cal.setTimeInMillis(date.getTime());
		return cal;
	}

	public static Calendar getCalendar(String sDate, String sFormat) {
		Date date = getDate(sDate, sFormat);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// cal.setTimeInMillis(date.getTime());
		return cal;
	}

	/**
	 * 
	 * @param sDate
	 *            sFormat yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static Calendar getCalendar2(String sDate, String sFormat) {
		Date date = getDate(sDate, sFormat);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// cal.setTimeInMillis(date.getTime());
		return cal;
	}

	/**
	 * 指定日期 +或- 分钟 sFormat: "yyyy-MM-dd HH:mm:ss"
	 */
	public static String calendarAddM(String sDate, int iTs, String sFormat) {
		Calendar cal = getCalendar2(sDate, sFormat);
		cal.add(Calendar.MINUTE, iTs);
		return calendarToTime(cal);
	}

	/**
	 * 指定日期 +或- 小时 sFormat: "yyyy-MM-dd HH:mm:ss"
	 */
	public static String calendarAddH(String sDate, int iTs, String sFormat) {
		Calendar cal = getCalendar2(sDate, sFormat);
		cal.add(Calendar.HOUR, iTs);
		return calendarToTime(cal);
	}

	/**
	 * 指定日期 +或- 天 sFormat: "yyyy-MM-dd HH:mm:ss"
	 */
	public static String calendarAddD(String sDate, int iTs, String sFormat) {
		Calendar cal = getCalendar2(sDate, sFormat);
		cal.add(Calendar.DATE, iTs);
		return calendarToTime(cal);
	}

	public static void main(String[] args) {
		// doPro();
		// String datetime = "2009-04-07 12:20:34";
		// String tmp = calendarAddD(datetime, -7, "yyyy-MM-dd");
		// DateUtil.getDate(DateUtil.getToday("yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd
		// HH:mm:ss")
		// getDate(String sDate, String sFormat)
		// System.out.println(getToday("yyyy-MM-dd HH:mm:ss"));
		// System.out.println(getDate(getToday("yyyy-MM-dd
		// HH:mm:ss"),"yyyy-MM-dd HH:mm:ss"));
		// System.out.println(DateUtil.getMonthBegin());
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
		String last = DateUtil.getDate(ca.getTime());
		System.out.println("===============first:" + last);
	}

	// 获取当月第一天
	public static String getFirstDay() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		String first = DateUtil.getDate(c.getTime());
		return first;
	}

	// 获取当月最后一天
	public static String getLastDay() {
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
		String last = DateUtil.getDate(ca.getTime());
		return last;
	}

	/**
	 * 指定日期+或-iTs天
	 */
	public static String calendarAdd(String sDate, int iTs) {
		Calendar cal = getCalendar(sDate, "yyyy-MM-dd");
		cal.add(Calendar.DATE, iTs);
		return calendarToDate(cal);
	}

	/**
	 * 指定日期+或-iTs天 是Time
	 */
	public static String calendarTimeAdd(String sDateTime, int iTs) {
		Calendar cal = getCalendar(sDateTime, "yyyy-MM-dd HH:mm:ss");
		cal.add(Calendar.DATE, iTs);
		return calendarToTime(cal);
	}

	/**
	 * 日历转换为 yyyy-MM-dd HH:mm:ss的字符串
	 */
	public static String calendarToDate(Calendar cal) {
		SimpleDateFormat sDateFormat = null;
		// java.util.Date date = new java.util.Date( cal.getTimeInMillis() );
		java.util.Date date = new java.util.Date(cal.getTime().getTime());
		sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return sDateFormat.format(date);
	}

	/**
	 * 日历转换为 yyyy-MM-dd HH:mm:ss的字符串
	 */
	public static String calendarToTime(Calendar cal) {
		SimpleDateFormat sDateFormat = null;
		// java.util.Date date = new java.util.Date( cal.getTimeInMillis() );
		java.util.Date date = new java.util.Date(cal.getTime().getTime());
		sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sDateFormat.format(date);
	}

	/**
	 * 得到 sBdate 与 sEdate 之间的天数(按实际天数计)
	 */
	public static int getDays(String sBdate, String sEdate) {
		Date dateB = parseDate(sBdate);
		Date dateE = parseDate(sEdate);
		return (int) ((dateE.getTime() - dateB.getTime()) / (3600 * 24 * 1000));
	}

	/**
	 * 当前日期 +,- 天数 ii 天数(可为负)
	 */
	public static String getTodayAdd(int ii) {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, ii);
		return calendarToDate(cal);
	}

	/**
	 * 取得指定年份月份的天数
	 */
	public static int getDaysMonth(int iYear, int iMonth) {
		int iFebDays = (iYear % 4 == 0 && (iYear % 100 != 0 || iYear % 400 == 0)) ? 29 : 28; // 闰年二月为29天
		int[] iDaysMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		iDaysMonth[1] = iFebDays;
		if (iMonth < 1 || iMonth > 12)
			return iDaysMonth[0];
		else
			return iDaysMonth[iMonth - 1];
	}

	public static int getDaysMonth(Calendar cal) {
		int iYear = cal.get(Calendar.YEAR); // 年
		int iMonth = cal.get(Calendar.MONTH) + 1; // 月
		return getDaysMonth(iYear, iMonth);
	}

	public static int getDaysMonth(String sDate) {
		return getDaysMonth(getCalendar(sDate));
	}

	/**
	 * 获取当前日期的年份
	 */
	public static String getYear() {
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy");
		java.util.Date date = new java.util.Date();
		return sFormat.format(date);
	}

	/**
	 * 获取当前日期的月份
	 */
	public static String getMonth() {
		SimpleDateFormat sFormat = new SimpleDateFormat("MM");
		java.util.Date date = new java.util.Date();
		return sFormat.format(date);
	}

	/**
	 * pdate：日期字符串 fpat:
	 */
	public static final String formatDate(String pdate, String fpat, String tpat) {

		if (pdate == null)
			return null;
		SimpleDateFormat formatter = new SimpleDateFormat(fpat);
		Date tmp;
		try {
			tmp = formatter.parse(pdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		formatter.applyPattern(tpat);
		return formatter.format(tmp);
	}

	/** 计算2个日期的天数差：后面日期 - 前面日期 **/
	public static int daysBetween(Date smdate, Date bdate) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}
}
