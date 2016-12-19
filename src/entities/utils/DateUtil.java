package entities.utils;

import java.util.Calendar;

public class DateUtil {
	public static boolean isInPayPeriod(Calendar theDate, Calendar startDate, Calendar endDate) {
		return theDate.compareTo(startDate) >= 0 && theDate.compareTo(endDate) <= 0;
	}
}
