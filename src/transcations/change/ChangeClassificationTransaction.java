package transcations.change;

import entities.Employee;
import entities.PaymentClassifications.PaymentClassification;
import entities.paymentSchedule.PaymentSchedule;

public abstract class ChangeClassificationTransaction extends ChangeEmployeeTransaction {

	public ChangeClassificationTransaction(int empId) {
		super(empId);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void change(Employee e) {
		// TODO Auto-generated method stub
		e.setClassification(getNewClassification());
		e.setPaymentSchedule(getNewSchedule());
	}
	
	public abstract PaymentClassification getNewClassification();
	public abstract PaymentSchedule getNewSchedule();

}
