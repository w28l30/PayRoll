package transcations.change;

import datasources.PayRollDatabase;
import entities.Employee;
import exceptions.InvalidOperationException;
import transcations.Transaction;

public abstract class ChangeEmployeeTransaction implements Transaction {

	private int empId;
	
	public ChangeEmployeeTransaction(int empId) {
		this.empId = empId;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		Employee e = PayRollDatabase.getInstance().getEmployee(empId);
		if (e != null) {
			change(e);
		} else {
			throw new InvalidOperationException("No such employee exists!");
		}
	}

	public abstract void change(Employee e);

}
