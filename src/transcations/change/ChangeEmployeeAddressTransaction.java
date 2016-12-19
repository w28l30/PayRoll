package transcations.change;

import entities.Employee;

public class ChangeEmployeeAddressTransaction extends ChangeEmployeeTransaction {
	private String newAddress;

	public ChangeEmployeeAddressTransaction(int empId, String newAddress) {
		super(empId);
		// TODO Auto-generated constructor stub
		this.newAddress = newAddress;
	}

	@Override
	public void change(Employee e) {
		// TODO Auto-generated method stub
		e.setAddress(newAddress);
	}

}
