package entities.PaymentClassifications;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import entities.PayCheck;
import entities.SalesReceipt;
import entities.utils.DateUtil;

public class CommissionClassification extends PaymentClassification {
    private double commissionRate;
    private double monthlySalary;
	private Map<Calendar, SalesReceipt> salesReceiptMap = new HashMap<>();
	
	public CommissionClassification(double commissionRate, double monthlySalary) {
		super();
		this.commissionRate = commissionRate;
		this.monthlySalary = monthlySalary;
	}

	public double getCommissionRate() {
		return commissionRate;
	}
	
	public double getMonthlySalary() {
		return monthlySalary;
	}

	public Map<Calendar, SalesReceipt> getSalesReceiptMap() {
		return salesReceiptMap;
	}
	
	public SalesReceipt getSalesReceipt(Calendar date) {
		return salesReceiptMap.get(date);
	}

	@Override
	public double caculatePay(PayCheck check) {
		// TODO Auto-generated method stub
		double totalPay = 0.0;
		for (SalesReceipt sr : salesReceiptMap.values()) {
			if (DateUtil.isInPayPeriod(sr.getDate(), check.getPayPeriodStartDate(), check.getPayPeriodEndDate())) {
				totalPay += sr.getAmount() * commissionRate;
			}
		}
		return totalPay + monthlySalary;
	}

	public void addSalesReceipt(SalesReceipt salesReceipt) {
		// TODO Auto-generated method stub
		salesReceiptMap.put(salesReceipt.getDate(), salesReceipt);
	}
	
	

}
