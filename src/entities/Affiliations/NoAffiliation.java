package entities.Affiliations;

import java.util.Calendar;

import entities.PayCheck;

public class NoAffiliation implements Affiliation {

	@Override
	public double caculateDeducations(PayCheck check) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int numberOfFridaysInPayPeriod(Calendar payPeriodStart, Calendar payPeriodEnd) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void addServiceCharge(Calendar date, double amount) {
		// TODO Auto-generated method stub
		
	}

}
