package entities;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class PayCheck {
	private double grossPay;
	private double deductions;
	private double netPay;
	private Calendar payPeriodStartDate;
	private Calendar payPeriodEndDate;
	
	private Map<String, String> fileds = new HashMap<>();
	
	public PayCheck(Calendar periodStart, Calendar payPeriodEndDate) {
		super();
		this.payPeriodStartDate = periodStart;
		this.payPeriodEndDate = payPeriodEndDate;
	}
	
	public Calendar getPayPeriodStartDate() {
		return this.payPeriodStartDate;
	}
	
	public Calendar getPayPeriodEndDate() {
		return this.payPeriodEndDate;
	}

	public double getGrossPay() {
		return grossPay;
	}

	public void setGrossPay(double grossPay) {
		this.grossPay = grossPay;
	}

	public double getDeductions() {
		return deductions;
	}

	public void setDeductions(double deductions) {
		this.deductions = deductions;
	}

	public double getNetPay() {
		return netPay;
	}

	public void setNetPay(double netPay) {
		this.netPay = netPay;
	}
	
	public String getField(String fieldName) {
		return fileds.get(fieldName);
	}
	
	public void setFiled(String name, String value) {
		fileds.put(name, value);
	}
	
	
}
