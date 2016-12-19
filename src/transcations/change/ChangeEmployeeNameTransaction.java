package transcations.change;

import entities.Employee;

public class ChangeEmployeeNameTransaction extends ChangeEmployeeTransaction {
	private String newName;

	public ChangeEmployeeNameTransaction(int empId, String newName) {
		super(empId);
		// TODO Auto-generated constructor stub
		this.newName = newName;
	}

	@Override
	public void change(Employee e) {
		// TODO Auto-generated method stub
		e.setName(newName);
	}

}
