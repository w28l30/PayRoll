package entities.paymentSchedule;

import java.util.Calendar;

public class MonthlySchedule implements PaymentSchedule {

	@Override
	public boolean isPayDate(Calendar payDate) {
		// TODO Auto-generated method stub
        Calendar nextDay = getNextDay(payDate);
        boolean isLastDayOfMonth = nextDay.get(Calendar.MONTH) != payDate.get(Calendar.MONTH);
        return isLastDayOfMonth;
	}

	private Calendar getNextDay(Calendar payDate) {
		// TODO Auto-generated method stub
        Calendar nextDay = (Calendar) payDate.clone();
        nextDay.add(Calendar.DAY_OF_MONTH, 1);
        return nextDay;
	}

	@Override
	public Calendar getPayPeriodStartDate(Calendar payDate) {
		// TODO Auto-generated method stub
        Calendar firstOfMonth = (Calendar) payDate.clone();
        firstOfMonth.set(Calendar.DAY_OF_MONTH, 1);
        return firstOfMonth;
	}
}
