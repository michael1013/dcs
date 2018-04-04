package cn.gnetop.pde.foundation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

	public static final String COMMON_DAY_FORMATE = "yyyy-MM-dd";

	public static final String COMMON_FORMATE = "yyyy-MM-dd HH:mm:ss";

	public static final String COMMON_FORMATE_SQL = "yyyy-MM-dd HH:mm:ss.S";

	public static String getDateString() {
		return getDateString(COMMON_FORMATE);
	}

	public static String getDateString(String formate) {
		return getDateString(new Date(), formate);
	}

	public static String getDateString(Date date) {
		return getDateString(date, COMMON_FORMATE);
	}

	public static String getDateString(Date date, String formate) {
		if (null == date) {
			date = new Date();
		}
		SimpleDateFormat sdf = new SimpleDateFormat(formate);
		return sdf.format(date);
	}

	public static long getMillis() {
		return System.currentTimeMillis();
	}

	public static Date getNow() {
		return new Date();
	}

	public static List<String> getDateListByDay(String begin, String end) {
		List<String> dateList = new ArrayList<>();
		try {
			Date beginDate = DateUtils.parseDate(begin, COMMON_DAY_FORMATE);
			Date endDate = DateUtils.parseDate(end, COMMON_DAY_FORMATE);

			for (Date dDate = beginDate; dDate.before(endDate); dDate = DateUtils.addDays(dDate, 1)) {
				dateList.add(DateUtils.getDateString(dDate, COMMON_DAY_FORMATE));
			}
			dateList.add(end);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateList;
	}

	public static String formate(Date date) {
		return formate(date, null);
	}

	public static String formate(Date date, String pattern) {
		if (StringUtils.isBlank(pattern)) {
			pattern = COMMON_FORMATE;
		}
		return new SimpleDateFormat(pattern).format(date);
	}

	/**
	 * 获取过去的第N(日|月|年)的日期
	 * 
	 * @param pass
	 *            过去的(日|月|年)
	 * @param type
	 *            DATE,MONTH,YEAR
	 * @return
	 */
	public static Date getPastDate(int pass, String type) {
		return getPastDate(pass, false, null, type);
	}

	/**
	 * TYPE目前只定义日,月,年 如果date不为空,则计算从date当前开始往前偏移pass(日|月|年)期的结果
	 * 如果date为空,则处理当天开始,往前偏移pass(日|月|年)期 如果clearTime为True,则设时分秒为0
	 * 
	 * @param pass
	 * @param clearTime
	 * @param date
	 * @return
	 */
	public static Date getPastDate(int pass, boolean clearTime, Date date, String type) {
		GregorianCalendar gcal = new GregorianCalendar();
		if (date != null) {
			gcal.setTime(date);
		} else {
			gcal.setTime(new Date());
		}
		if (StringUtils.equalsIgnoreCase(type, "YEAR")) {
			gcal.add(Calendar.YEAR, pass * (-1));
		} else if (StringUtils.equalsIgnoreCase(type, "MONTH")) {
			gcal.add(Calendar.MONTH, pass * (-1));
		} else {
			gcal.add(Calendar.DATE, pass * (-1));
		}

		if (clearTime) {
			gcal.set(Calendar.HOUR_OF_DAY, 0);
			gcal.set(Calendar.MINUTE, 0);
			gcal.set(Calendar.SECOND, 0);
		}
		return gcal.getTime();
	}

	/**
	 * 获取过去的第N天的日期，若clearTime为TRUE则都将时分秒置为0
	 * 
	 * @param pass
	 * @param clearTime
	 * @return
	 */
	public static Date getPastDate(int pass, boolean clearTime, String type) {
		return getPastDate(pass, clearTime, null, type);
	}

	public static void main(String[] args) {
		System.out.println(getDateString(getPastDate(1, "day"), "yyyy-MM-dd"));
	}
}
