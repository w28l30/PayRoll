package transcations.add;

import entities.PaymentClassifications.CommissionClassification;
import entities.PaymentClassifications.PaymentClassification;
import entities.paymentSchedule.BiWeeklySchedule;
import entities.paymentSchedule.PaymentSchedule;

public class AddCommissionedEmployeeTransaction extends AddEmployeeTransaction {
	private double commissionRate;
	private double monthlySalary;
	

	public AddCommissionedEmployeeTransaction(int empid, String name, String address, double commissionRate, double monthlySalary) {
		super(empid, name, address);
		// TODO Auto-generated constructor stub
		this.commissionRate = commissionRate;
		this.monthlySalary = monthlySalary;
	}

	@Override
	protected PaymentSchedule makeSchedule() {
		// TODO Auto-generated method stub
		return new BiWeeklySchedule();
	}

	@Override
	protected PaymentClassification makeClassification() {
		// TODO Auto-generated method stub
		return new CommissionClassification(commissionRate, monthlySalary);
	}
	
	

}
