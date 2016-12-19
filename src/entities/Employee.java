package entities;

import java.util.Calendar;

import entities.Affiliations.Affiliation;
import entities.Affiliations.NoAffiliation;
import entities.PaymentClassifications.PaymentClassification;
import entities.paymentMethods.PaymentMethod;
import entities.paymentSchedule.PaymentSchedule;

public class Employee {
	private int empid;
	private String name;
	private String address;
	private PaymentClassification classification;
	private PaymentMethod method;
	private PaymentSchedule schedule;
	private Affiliation affiliation = new NoAffiliation();
	
	public Employee(int empid, String name, String address) {
		super();
		this.empid = empid;
		this.name = name;
		this.address = address;
	}
	
	public boolean isPayDate(Calendar date) {
		return schedule.isPayDate(date);
	}
	
	public Calendar getPayPeriodStartDate(Calendar payDate) {
		return schedule.getPayPeriodStartDate(payDate);
	}
	
	public void payDay(PayCheck payCheck) {
		double grossPay = classification.caculatePay(payCheck);
		double deductions = affiliation.caculateDeducations(payCheck);
		double netPay = grossPay - deductions;
		payCheck.setGrossPay(grossPay);
		payCheck.setDeductions(deductions);
		payCheck.setNetPay(netPay);
		method.pay(payCheck);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public PaymentClassification getClassification() {
		return classification;
	}

	public void setClassification(PaymentClassification classification) {
		this.classification = classification;
	}

	public PaymentMethod getMethod() {
		return method;
	}

	public void setMethod(PaymentMethod method) {
		this.method = method;
	}

	public PaymentSchedule getPaymentSchedule() {
		return schedule;
	}

	public void setPaymentSchedule(PaymentSchedule schedule) {
		this.schedule = schedule;
	}

	public int getEmpid() {
		return empid;
	}

	public PaymentSchedule getSchedule() {
		return schedule;
	}

	public Affiliation getAffiliation() {
		return affiliation;
	}
	
	public void setAffiliation(Affiliation affiliation) {
		this.affiliation = affiliation;
	}
	
}
