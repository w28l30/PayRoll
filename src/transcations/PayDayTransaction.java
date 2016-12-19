package transcations;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import datasources.PayRollDatabase;
import entities.Employee;
import entities.PayCheck;

public class PayDayTransaction implements Transaction {
	
	private Calendar payDate;
	private Map<Integer, PayCheck> payCheckMap = new HashMap<>();
	private PayRollDatabase database = PayRollDatabase.getInstance();

	public PayDayTransaction(Calendar payDate) {
		super();
		this.payDate = payDate;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		for (int empId : database.getAllEmployees()) {
			Employee e = database.getEmployee(empId);
			if (e.isPayDate(payDate)) {
				PayCheck payCheck = new PayCheck(e.getPayPeriodStartDate(payDate), payDate);
				payCheckMap.put(empId, payCheck);
				e.payDay(payCheck);
			}
		}
	}
	
	public PayCheck getPayCheck(int empId) {
		return payCheckMap.get(empId);
	}

}
