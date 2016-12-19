package entities.paymentSchedule;

import java.util.Calendar;

public interface PaymentSchedule {
	boolean isPayDate(Calendar date);
	Calendar getPayPeriodStartDate(Calendar payDate);
}


