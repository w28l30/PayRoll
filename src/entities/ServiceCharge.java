package entities;

import java.util.Calendar;

public class ServiceCharge {
	private double amount;
	private Calendar date;
	
	public ServiceCharge(double amount, Calendar date) {
		super();
		this.amount = amount;
		this.date = date;
	}

	public double getAmount() {
		return amount;
	}

	public Calendar getDate() {
		return date;
	}
	
	
	
	
}
