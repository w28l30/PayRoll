package transcations.change;

import entities.PaymentClassifications.HourlyClassification;
import entities.PaymentClassifications.PaymentClassification;
import entities.paymentSchedule.PaymentSchedule;
import entities.paymentSchedule.WeeklySchedule;

public class ChangeHourlyClassification extends ChangeClassificationTransaction {
	private double hourlyRate;
	
	public ChangeHourlyClassification(int empId, double hourlyRate) {
		super(empId);
		// TODO Auto-generated constructor stub
		this.hourlyRate = hourlyRate;
	}

	public PaymentClassification getNewClassification() {
		return new HourlyClassification(hourlyRate);
	}
	
	public PaymentSchedule getNewSchedule() {
		return new WeeklySchedule();
	}
	
}
