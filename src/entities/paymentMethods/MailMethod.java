package entities.paymentMethods;

import entities.PayCheck;

public class MailMethod implements PaymentMethod {

	@Override
	public void pay(PayCheck check) {
		// TODO Auto-generated method stub
		check.setFiled("Disposition", "hold");
	}

}
