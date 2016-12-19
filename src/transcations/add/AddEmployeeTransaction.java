package transcations.add;

import datasources.PayRollDatabase;
import entities.Employee;
import entities.PaymentClassifications.PaymentClassification;
import entities.paymentMethods.HoldMethod;
import entities.paymentMethods.PaymentMethod;
import entities.paymentSchedule.PaymentSchedule;
import transcations.Transaction;

public abstract class AddEmployeeTransaction implements Transaction {
	private int empid;
	private String name;
	private String address;
	
	public AddEmployeeTransaction(int empid, String name, String address) {
		this.empid = empid;
		this.name = name;
		this.address = address;
	}

	protected abstract PaymentSchedule makeSchedule();
	
	protected abstract PaymentClassification makeClassification();
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		Employee e = new Employee(empid, name, address);
		
		PaymentClassification pc = makeClassification();
		PaymentMethod pm = new HoldMethod();
		PaymentSchedule ps = makeSchedule();
		
		e.setMethod(pm);
		e.setPaymentSchedule(ps);
		e.setClassification(pc);
		
		PayRollDatabase.getInstance().addEmployee(empid, e);
	}
}
