package transcations.add;

import java.util.Calendar;

import datasources.PayRollDatabase;
import entities.Employee;
import entities.TimeCard;
import entities.PaymentClassifications.HourlyClassification;
import entities.PaymentClassifications.PaymentClassification;
import transcations.Transaction;

public class AddTimeCardTransaction implements Transaction {
	private Calendar date;
	private double hours;
	private int id;
	
	public AddTimeCardTransaction(Calendar date, double hours, int id) {
		super();
		this.date = date;
		this.hours = hours;
		this.id = id;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		Employee employee = PayRollDatabase.getInstance().getEmployee(id);
		if (employee != null) {
			PaymentClassification pc = employee.getClassification();
			if (pc instanceof HourlyClassification) {
				((HourlyClassification) pc).addTimeCard(new TimeCard(date, hours));
			}
		}
	}
	
}
