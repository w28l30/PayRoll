package transcations.add;

import java.util.Calendar;

import datasources.PayRollDatabase;
import entities.Employee;
import entities.Affiliations.Affiliation;
import transcations.Transaction;

public class AddServiceChargeTransaction implements Transaction {
	
	public AddServiceChargeTransaction(int memberId, Calendar date, double amount) {
		Employee employee = PayRollDatabase.getInstance().getUnionMember(memberId);
		Affiliation affiliation = employee.getAffiliation();
		affiliation.addServiceCharge(date, amount);
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
	}

}
