package transcations.change;

import datasources.PayRollDatabase;
import entities.Employee;
import entities.Affiliations.Affiliation;
import entities.Affiliations.NoAffiliation;
import entities.Affiliations.UnionAffiliation;

public class ChangeUnionAffiliationTransaction extends ChangeAffiliationTransaction {
	private int memberId;
	private double dues;

	public ChangeUnionAffiliationTransaction(int empId, int memberId, double dues) {
		super(empId);
		// TODO Auto-generated constructor stub
		this.dues = dues;
		this.memberId = memberId;

	}

	@Override
	public Affiliation getNewAffiliation() {
		// TODO Auto-generated method stub
		return new UnionAffiliation(memberId, dues);
	}

	@Override
	public void recordMemberShip(Employee e) {
		// TODO Auto-generated method stub
		PayRollDatabase.getInstance().addUnionMember(memberId, e);
	}

}
