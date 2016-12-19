package entities.PaymentClassifications;

import entities.PayCheck;

public class SalariedClassification extends PaymentClassification {
	private double monthlySalary;

	public SalariedClassification(double monthlySalary) {
		super();
		this.monthlySalary = monthlySalary;
	}

	@Override
	public double caculatePay(PayCheck check) {
		// TODO Auto-generated method stub
		return monthlySalary;
	}

	public Object getSalary() {
		// TODO Auto-generated method stub
		return monthlySalary;
	}

}
