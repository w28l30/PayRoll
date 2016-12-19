package entities.paymentSchedule;

import java.util.Calendar;

public class BiWeeklySchedule implements PaymentSchedule {

	@Override
	public boolean isPayDate(Calendar payDate) {
		// TODO Auto-generated method stub
        boolean evenCalendarWeek = payDate.get(Calendar.WEEK_OF_YEAR) % 2 == 0;
        return payDate.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY && evenCalendarWeek;
	}

	@Override
	public Calendar getPayPeriodStartDate(Calendar payDate) {
		// TODO Auto-generated method stub
        Calendar payPeriodStartDate = (Calendar) payDate.clone();
        payPeriodStartDate.add(Calendar.DAY_OF_MONTH, -13);
        return payPeriodStartDate;
	}

}
