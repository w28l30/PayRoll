package entities.paymentSchedule;

import java.util.Calendar;

public class WeeklySchedule implements PaymentSchedule {
	@Override
	public boolean isPayDate(Calendar date) {
		// TODO Auto-generated method stub
		return date.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY;
	}

	@Override
	public Calendar getPayPeriodStartDate(Calendar payDate) {
		// TODO Auto-generated method stub
        Calendar monday = (Calendar) payDate.clone();
        monday.roll(Calendar.DAY_OF_MONTH, -5);
        return monday;
	}
}
