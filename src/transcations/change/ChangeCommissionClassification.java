package transcations.change;

import entities.PaymentClassifications.CommissionClassification;
import entities.PaymentClassifications.PaymentClassification;
import entities.paymentSchedule.BiWeeklySchedule;
import entities.paymentSchedule.PaymentSchedule;

public class ChangeCommissionClassification extends ChangeClassificationTransaction {
	private double monthlySalary;
	private double commissionRate;
	
	public ChangeCommissionClassification(int empId, double monthlySalary, double commissionRate) {
		super(empId);
		// TODO Auto-generated constructor stub
		this.monthlySalary = monthlySalary;
		this.commissionRate = commissionRate;
	}

	public PaymentClassification getNewClassification() {
		return new CommissionClassification(commissionRate, monthlySalary);
	}
	
	public PaymentSchedule getNewSchedule() {
		return new BiWeeklySchedule();
	}
}
