package entities.PaymentClassifications;

import entities.PayCheck;

public abstract class PaymentClassification {
	public abstract double caculatePay(PayCheck check);
}
