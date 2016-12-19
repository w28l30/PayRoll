package entities.PaymentClassifications;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import entities.PayCheck;
import entities.TimeCard;
import entities.utils.DateUtil;

public class HourlyClassification extends PaymentClassification {
	private double hourlyRate;
	private Map<Calendar, TimeCard> timeCardMap = new HashMap<>();
	
	public HourlyClassification(double hourlyRate) {
		this.hourlyRate = hourlyRate;
	}
	
	public void addTimeCard(TimeCard card) {
		timeCardMap.put(card.getDate(), card);
	}
	
	public TimeCard getTimeCard(Calendar date) {
		return timeCardMap.get(date);
	}
	
	public double getHourlyRate() {
		return this.hourlyRate;
	}

	@Override
	public double caculatePay(PayCheck check) {
		// TODO Auto-generated method stub
		double totalPay = 0;
		for (TimeCard tc : timeCardMap.values()) {
			if (DateUtil.isInPayPeriod(tc.getDate(), check.getPayPeriodStartDate(), check.getPayPeriodEndDate())) {
				totalPay += caculateForTimeCard(tc);
			}
		}
		return totalPay;
	}
	
	public double caculateForTimeCard(TimeCard timeCard) {
		double hours = timeCard.getHours();
		double overTime = Math.max(0.0, hours - 8.0);
		double straightTime = hours - overTime;
		return overTime * 1.5 * hourlyRate + straightTime * hourlyRate;
	}

}
