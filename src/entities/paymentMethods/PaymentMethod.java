package entities.paymentMethods;

import entities.PayCheck;

public interface PaymentMethod {
	void pay(PayCheck check);
}
