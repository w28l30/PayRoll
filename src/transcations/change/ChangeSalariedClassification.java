package transcations.change;

import entities.PaymentClassifications.PaymentClassification;
import entities.PaymentClassifications.SalariedClassification;
import entities.paymentSchedule.MonthlySchedule;
import entities.paymentSchedule.PaymentSchedule;

public class ChangeSalariedClassification extends ChangeClassificationTransaction {
	private double newMonthlySalary;
	
	public ChangeSalariedClassification(int empId, double newMonthlySalary) {
		super(empId);
		// TODO Auto-generated constructor stub
		this.newMonthlySalary = newMonthlySalary;
	}

	public PaymentClassification getNewClassification() {
		return new SalariedClassification(newMonthlySalary);
	}
	
	public PaymentSchedule getNewSchedule() {
		return new MonthlySchedule();
	}
}
