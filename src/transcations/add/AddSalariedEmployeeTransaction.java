package transcations.add;

import entities.PaymentClassifications.PaymentClassification;
import entities.PaymentClassifications.SalariedClassification;
import entities.paymentSchedule.MonthlySchedule;
import entities.paymentSchedule.PaymentSchedule;

public class AddSalariedEmployeeTransaction extends AddEmployeeTransaction {
	private double monthlySalary;
	
	public AddSalariedEmployeeTransaction(int empid, String name, String address, double monthlySalary) {
		super(empid, name, address);
		// TODO Auto-generated constructor stub
		this.monthlySalary = monthlySalary;
	}

	@Override
	protected PaymentSchedule makeSchedule() {
		// TODO Auto-generated method stub
		return new MonthlySchedule();
	}

	@Override
	protected PaymentClassification makeClassification() {
		// TODO Auto-generated method stub
		return new SalariedClassification(monthlySalary);
	}

}
