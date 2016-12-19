package transcations.delete;

import datasources.PayRollDatabase;
import transcations.Transaction;

public class DeleteEmployeeTransaction implements Transaction {
	private int empid;
	
	public DeleteEmployeeTransaction(int empid) {
		this.empid = empid;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		PayRollDatabase database = PayRollDatabase.getInstance();
		database.deleteEmployee(empid);
	}
	
}
