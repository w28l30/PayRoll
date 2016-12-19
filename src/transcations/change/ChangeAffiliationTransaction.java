package transcations.change;

import entities.Employee;
import entities.Affiliations.Affiliation;

public abstract class ChangeAffiliationTransaction extends ChangeEmployeeTransaction {

	public ChangeAffiliationTransaction(int empId) {
		super(empId);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void change(Employee e) {
		// TODO Auto-generated method stub
		recordMemberShip(e);
		e.setAffiliation(getNewAffiliation());
	}
	
	public abstract Affiliation getNewAffiliation();
	public abstract void recordMemberShip(Employee e);
}
