package transcations.change;

import datasources.PayRollDatabase;
import entities.Employee;
import entities.Affiliations.Affiliation;
import entities.Affiliations.NoAffiliation;
import entities.Affiliations.UnionAffiliation;

public class ChangeUnAffiliationTransaction extends ChangeAffiliationTransaction {

	public ChangeUnAffiliationTransaction(int empId) {
		super(empId);
		// TODO Auto-generated constructor stub

	}

	@Override
	public Affiliation getNewAffiliation() {
		// TODO Auto-generated method stub
		return new NoAffiliation();
	}

	@Override
	public void recordMemberShip(Employee e) {
		// TODO Auto-generated method stub
		Affiliation affiliation = e.getAffiliation();
		if (affiliation instanceof UnionAffiliation) {
			PayRollDatabase.getInstance().deleteUnionMember(((UnionAffiliation) affiliation).getMemberId());
		}
	}
	

}
