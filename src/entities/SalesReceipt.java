package entities;

import java.util.Calendar;

public class SalesReceipt {
	private Calendar date;
	private double amount;
	
	public SalesReceipt(Calendar date, double amount) {
		super();
		this.date = date;
		this.amount = amount;
	}
	public Calendar getDate() {
		return date;
	}
	public double getAmount() {
		return amount;
	}
	
	
}
