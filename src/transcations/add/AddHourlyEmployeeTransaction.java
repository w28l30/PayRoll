package transcations.add;

import entities.PaymentClassifications.HourlyClassification;
import entities.PaymentClassifications.PaymentClassification;
import entities.paymentSchedule.PaymentSchedule;
import entities.paymentSchedule.WeeklySchedule;

public class AddHourlyEmployeeTransaction extends AddEmployeeTransaction {
	private double hourlyRate;

	public AddHourlyEmployeeTransaction(int empid, String name, String address, double hourlyRate) {
		// TODO Auto-generated constructor stub
		super(empid, name, address);
		this.hourlyRate = hourlyRate;
	}

	@Override
	protected PaymentSchedule makeSchedule() {
		// TODO Auto-generated method stub
		return new WeeklySchedule();
	}

	@Override
	protected PaymentClassification makeClassification() {
		// TODO Auto-generated method stub
		return new HourlyClassification(hourlyRate);
	}

}
