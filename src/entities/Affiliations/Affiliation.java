package entities.Affiliations;

import java.util.Calendar;

import entities.PayCheck;

public interface Affiliation {
	public abstract double caculateDeducations(PayCheck check);
	public abstract int numberOfFridaysInPayPeriod(Calendar payPeriodStart, Calendar payPeriodEnd);
	public abstract void addServiceCharge(Calendar date, double amount);
}
