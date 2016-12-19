package entities.Affiliations;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import entities.PayCheck;
import entities.ServiceCharge;
import entities.utils.DateUtil;

public class UnionAffiliation implements Affiliation {
	private double dues;
	private int memberId;
	private Map<Calendar, ServiceCharge> serviceChargeMap = new HashMap<>();
	

	public UnionAffiliation(int memberId, double dues) {
		super();
		this.dues = dues;
		this.memberId = memberId;
	}

	public void addServiceCharge(Calendar date, double amount) {
		serviceChargeMap.put(date, new ServiceCharge(amount, date));
	}

	@Override
	public double caculateDeducations(PayCheck check) {
		// TODO Auto-generated method stub
		double totalDeducations = 0.0;
		totalDeducations += numberOfFridaysInPayPeriod(check.getPayPeriodStartDate(), check.getPayPeriodEndDate()) * dues;
		for (ServiceCharge sc : serviceChargeMap.values()) {
			if (DateUtil.isInPayPeriod(sc.getDate(), check.getPayPeriodStartDate(), check.getPayPeriodEndDate())) {
				totalDeducations += sc.getAmount();
			}
		}
		return totalDeducations;
	}

	@Override
	public int numberOfFridaysInPayPeriod(Calendar payPeriodStart, Calendar payPeriodEnd) {
		// TODO Auto-generated method stub
        int numberOfFridays = 0;
        payPeriodStart = (Calendar) payPeriodStart.clone();
        while (!payPeriodStart.after(payPeriodEnd)){
            if (payPeriodStart.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY){
                numberOfFridays++;
            }
            payPeriodStart.add(Calendar.DAY_OF_MONTH, 1);
        }
        return numberOfFridays;
	}

	public double getDues() {
		return dues;
	}

	public int getMemberId() {
		return memberId;
	}
	
	public ServiceCharge getServiceCharge(Calendar date) {
		return serviceChargeMap.get(date);
	}
	
	

}
