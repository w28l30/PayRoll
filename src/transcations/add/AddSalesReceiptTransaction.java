package transcations.add;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.Calendar;

import datasources.PayRollDatabase;
import entities.Employee;
import entities.SalesReceipt;
import entities.PaymentClassifications.CommissionClassification;
import entities.PaymentClassifications.PaymentClassification;
import transcations.Transaction;

public class AddSalesReceiptTransaction implements Transaction {
	private int id;
	private double amount;
	private Calendar date;
	
	public AddSalesReceiptTransaction(int id, double amount, Calendar date) {
		super();
		this.id = id;
		this.amount = amount;
		this.date = date;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		Employee employee = PayRollDatabase.getInstance().getEmployee(id);
		if (employee != null) {
			PaymentClassification paymentClassification = employee.getClassification();
			if (paymentClassification instanceof CommissionClassification) {
				CommissionClassification commissionedPaymentClassification =
                        (CommissionClassification) paymentClassification;
                commissionedPaymentClassification.addSalesReceipt(new SalesReceipt(date, amount));
			}
		}
	}

}
