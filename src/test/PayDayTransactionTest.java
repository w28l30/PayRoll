package test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Calendar;
import java.util.GregorianCalendar;


import org.junit.Rule;
import org.junit.Test;

import entities.PayCheck;
import transcations.PayDayTransaction;
import transcations.add.AddCommissionedEmployeeTransaction;
import transcations.add.AddHourlyEmployeeTransaction;
import transcations.add.AddSalariedEmployeeTransaction;
import transcations.add.AddSalesReceiptTransaction;
import transcations.add.AddServiceChargeTransaction;
import transcations.add.AddTimeCardTransaction;
import transcations.change.ChangeUnionAffiliationTransaction;

public class PayDayTransactionTest {
	@Rule
	public DatabaseResource database = new DatabaseResource();
	private final GregorianCalendar THURSDAY = new GregorianCalendar(2001, 10, 8);
	private final GregorianCalendar FRIDAY = new GregorianCalendar(2001, 10, 9);
	private final GregorianCalendar LAST_FRIDAY = new GregorianCalendar(2001, 10, 2);

	@Test
	public void paySingleSalariedEmpolyee() {
		int employeeId = 1;
		AddSalariedEmployeeTransaction addSalariedEmployeeTransaction = new AddSalariedEmployeeTransaction(employeeId,
				"Bob", "Home", 1000.0);
		addSalariedEmployeeTransaction.execute();

		Calendar payDate = new GregorianCalendar(2016, 10, 30);
		PayDayTransaction paydayTransaction = new PayDayTransaction(payDate);
		paydayTransaction.execute();

		PayCheck payCheck = paydayTransaction.getPayCheck(employeeId);
		assertThat(payCheck.getDeductions(), is(0.0));
		assertThat(payCheck.getPayPeriodEndDate(), is(payDate));
		assertThat(payCheck.getGrossPay(), is(1000.0));
		assertThat(payCheck.getField("Disposition"), is("hold"));
	}

	@Test
	public void paySingleSalariedOnWrongDate() {
		int employeeId = 1;
		AddSalariedEmployeeTransaction addSalariedEmployeeTransaction = new AddSalariedEmployeeTransaction(employeeId,
				"Bob", "Home", 1000.0);
		addSalariedEmployeeTransaction.execute();

		Calendar payDate = new GregorianCalendar(2016, 11, 30);
		PayDayTransaction paydayTransaction = new PayDayTransaction(payDate);
		paydayTransaction.execute();

		PayCheck payCheck = paydayTransaction.getPayCheck(employeeId);
		assertThat(payCheck, nullValue());
	}

	@Test
	public void testPaySingleHourlyEmployeeNoTimeCards() {
		int employeeId = 1;
		AddHourlyEmployeeTransaction addHourlyEmployeeTransaction = new AddHourlyEmployeeTransaction(employeeId, "Bill",
				"ABC", 20.5);
		addHourlyEmployeeTransaction.execute();

		PayDayTransaction paydayTransaction = new PayDayTransaction(FRIDAY);
		paydayTransaction.execute();

		validateHourlyPayCheck(paydayTransaction, employeeId, FRIDAY, 0.0);
	}

	@Test
	public void testPaySingleHourlyEmployeeWithTimeCards() {
		int employeeId = 1;
		AddHourlyEmployeeTransaction addHourlyEmployeeTransaction = new AddHourlyEmployeeTransaction(employeeId, "Bill",
				"ABC", 20.5);
		addHourlyEmployeeTransaction.execute();

		AddTimeCardTransaction addTimeCardTransaction = new AddTimeCardTransaction(THURSDAY, 2, 1);
		addTimeCardTransaction.execute();

		PayDayTransaction paydayTransaction = new PayDayTransaction(FRIDAY);
		paydayTransaction.execute();

		validateHourlyPayCheck(paydayTransaction, employeeId, FRIDAY, 41.0);
	}

	@Test
	public void testPaySingleHourlyEmployeeWithTwoTimeCards() {
		int employeeId = 1;
		AddHourlyEmployeeTransaction addHourlyEmployeeTransaction = new AddHourlyEmployeeTransaction(employeeId, "Bill",
				"ABC", 20.5);
		addHourlyEmployeeTransaction.execute();

		AddTimeCardTransaction addTimeCardTransaction1 = new AddTimeCardTransaction(THURSDAY, 2, 1);
		addTimeCardTransaction1.execute();

		AddTimeCardTransaction addTimeCardTransaction2 = new AddTimeCardTransaction(FRIDAY, 7, 1);
		addTimeCardTransaction2.execute();

		PayDayTransaction paydayTransaction = new PayDayTransaction(FRIDAY);
		paydayTransaction.execute();

		validateHourlyPayCheck(paydayTransaction, employeeId, FRIDAY, 9 * 20.5);
	}

	@Test
	public void testPaySingleHourlyEmployeeWithSpinningTwoTimeCards() {
		int employeeId = 1;
		AddHourlyEmployeeTransaction addHourlyEmployeeTransaction = new AddHourlyEmployeeTransaction(employeeId, "Bill",
				"ABC", 20.5);
		addHourlyEmployeeTransaction.execute();

		AddTimeCardTransaction addTimeCardTransaction1 = new AddTimeCardTransaction(THURSDAY, 2, 1);
		addTimeCardTransaction1.execute();

		AddTimeCardTransaction addTimeCardTransaction2 = new AddTimeCardTransaction(LAST_FRIDAY, 7, 1);
		addTimeCardTransaction2.execute();

		PayDayTransaction paydayTransaction = new PayDayTransaction(FRIDAY);
		paydayTransaction.execute();

		validateHourlyPayCheck(paydayTransaction, employeeId, FRIDAY, 2 * 20.5);
	}

	@Test
	public void testPaySingleHourlyEmployeeWithOverTimeCards() {
		int employeeId = 1;
		AddHourlyEmployeeTransaction addHourlyEmployeeTransaction = new AddHourlyEmployeeTransaction(employeeId, "Bill",
				"ABC", 20.5);
		addHourlyEmployeeTransaction.execute();

		AddTimeCardTransaction addTimeCardTransaction = new AddTimeCardTransaction(THURSDAY, 10, 1);
		addTimeCardTransaction.execute();

		PayDayTransaction paydayTransaction = new PayDayTransaction(FRIDAY);
		paydayTransaction.execute();

		validateHourlyPayCheck(paydayTransaction, employeeId, FRIDAY, 20.5 * 8 + 2 * 20.5 * 1.5);
	}

	@Test
	public void testPaySingleHourlyEmployeeWithWrongDate() {
		int employeeId = 1;
		AddHourlyEmployeeTransaction addHourlyEmployeeTransaction = new AddHourlyEmployeeTransaction(employeeId, "Bill",
				"ABC", 20.5);
		addHourlyEmployeeTransaction.execute();

		AddTimeCardTransaction addTimeCardTransaction = new AddTimeCardTransaction(THURSDAY, 2, 1);
		addTimeCardTransaction.execute();

		PayDayTransaction paydayTransaction = new PayDayTransaction(THURSDAY);
		paydayTransaction.execute();

		assertThat(paydayTransaction.getPayCheck(employeeId), is(nullValue()));
	}

	@Test
	public void testSalariedUnionMember() {
    	int empId = 1;
    	AddSalariedEmployeeTransaction addSalariedEmployeeTransaction = new AddSalariedEmployeeTransaction(empId, "Bill", "Home", 1000.0);
    	addSalariedEmployeeTransaction.execute();
    	int	memberId = 1231;
    	ChangeUnionAffiliationTransaction changeUnionAffiliationTransaction = new ChangeUnionAffiliationTransaction(empId, memberId, 10.0);
    	changeUnionAffiliationTransaction.execute();
    	Calendar payDate = new GregorianCalendar(2001, 10, 30);
    	PayDayTransaction payDayTransaction = new PayDayTransaction(payDate);
    	payDayTransaction.execute();
    	
    	PayCheck payCheck = payDayTransaction.getPayCheck(empId);
    	assertThat(payCheck.getGrossPay(), is(1000.0));
    	assertThat(payCheck.getDeductions(), is(50.0));
    	assertThat(payCheck.getNetPay(), is(950.0));
    }
	
	@Test
	public void testSingleCommissionedEmployeeWithOneReceipt() {
		int empId = 2;
        double commissionRate = 0.5;
        double monthlySalary = 700.0;
        double receiptAmount = 600.0;
		AddCommissionedEmployeeTransaction addCommissionedEmployeeTransaction = new AddCommissionedEmployeeTransaction(empId, "Bill", "ABCD", commissionRate, monthlySalary);
		addCommissionedEmployeeTransaction.execute();
		
		Calendar payDate = new GregorianCalendar(2001, 10, 16);
		AddSalesReceiptTransaction addSalesReceiptTransaction = new AddSalesReceiptTransaction(2, receiptAmount, payDate);
		addSalesReceiptTransaction.execute();
		
		PayDayTransaction payDayTransaction = new PayDayTransaction(payDate);
		payDayTransaction.execute();
		
		double expectedGrosspay = monthlySalary + receiptAmount * commissionRate;
		validateCommissionedPayCheck(payDayTransaction, empId, payDate, expectedGrosspay);
	}
	
	@Test
	public void testSingleCommissionedEmployeeWithNoReceipt() {
		int empId = 2;
        double commissionRate = 0.5;
        double monthlySalary = 700.0;
		AddCommissionedEmployeeTransaction addCommissionedEmployeeTransaction = new AddCommissionedEmployeeTransaction(empId, "Bill", "ABCD", commissionRate, monthlySalary);
		addCommissionedEmployeeTransaction.execute();
		
		Calendar payDate = new GregorianCalendar(2001, 10, 16);
		
		PayDayTransaction payDayTransaction = new PayDayTransaction(payDate);
		payDayTransaction.execute();
		
		double expectedGrosspay = monthlySalary;
		validateCommissionedPayCheck(payDayTransaction, empId, payDate, expectedGrosspay);
	}
	
	@Test
	public void testHourlyUnionMemberServiceCharge() {
        int employeeId = 1;
        Calendar payDate = new GregorianCalendar(2001, 10, 30);
        int memberId = 7734;
        double weeklyUnionDues = 9.5;
        double hourlyRate = 20.0;

        AddHourlyEmployeeTransaction addEmployeeTransaction = new AddHourlyEmployeeTransaction(employeeId, "Bob", "Home", hourlyRate);
        addEmployeeTransaction.execute();

        ChangeUnionAffiliationTransaction changeUnionAffiliationTransaction = new ChangeUnionAffiliationTransaction(employeeId, memberId, weeklyUnionDues);
        changeUnionAffiliationTransaction.execute();

        double serviceCharge = 19.5;
        AddServiceChargeTransaction addServiceChargeTransaction = new AddServiceChargeTransaction(memberId, payDate, serviceCharge);
        addServiceChargeTransaction.execute();

        AddTimeCardTransaction addTimeCardTransaction = new AddTimeCardTransaction(payDate, 8.0, employeeId);
        addTimeCardTransaction.execute();

        PayDayTransaction paydayTransaction = new PayDayTransaction(payDate);
        paydayTransaction.execute();
        
        PayCheck payCheck = paydayTransaction.getPayCheck(employeeId);
        assertThat(payCheck.getPayPeriodEndDate(), is(payDate));
        assertThat(payCheck.getGrossPay(), is(8 * hourlyRate));
        assertThat("hold", is(payCheck.getField("Disposition")));
        assertThat(payCheck.getDeductions(), is(weeklyUnionDues + serviceCharge));
        assertThat(payCheck.getNetPay(), is(8 * hourlyRate - (weeklyUnionDues + serviceCharge)));

	}
	

	@Test
	public void testServiceChargesSpanningMultiplePayPeriods() {
        int employeeId = 1;
        Calendar payDate = new GregorianCalendar(2001, 10, 30);
        Calendar previousPayDate = (Calendar) payDate.clone();
        previousPayDate.add(Calendar.DAY_OF_WEEK, -7);
        Calendar nextPayDate = (Calendar) payDate.clone();
        nextPayDate.add(Calendar.DAY_OF_WEEK, 7);
        int memberId = 7734;
        double weeklyUnionDues = 9.5;
        double hourlyRate = 20.0;

        AddHourlyEmployeeTransaction addEmployeeTransaction = new AddHourlyEmployeeTransaction(employeeId, "Bob", "Home", hourlyRate);
        addEmployeeTransaction.execute();

        ChangeUnionAffiliationTransaction changeUnionAffiliationTransaction = new ChangeUnionAffiliationTransaction(employeeId, memberId, weeklyUnionDues);
        changeUnionAffiliationTransaction.execute();

        double serviceCharge = 19.5;
        AddServiceChargeTransaction addServiceChargeTransaction = new AddServiceChargeTransaction(memberId, payDate, serviceCharge);
        addServiceChargeTransaction.execute();
        
        AddServiceChargeTransaction lateServiceChargeTransaction = new AddServiceChargeTransaction(memberId, previousPayDate, 100.0);
        lateServiceChargeTransaction.execute();

        AddServiceChargeTransaction earlyServiceChargeTransaction = new AddServiceChargeTransaction(memberId, nextPayDate, 200.0);
        earlyServiceChargeTransaction.execute();


        AddTimeCardTransaction addTimeCardTransaction = new AddTimeCardTransaction(payDate, 8.0, employeeId);
        addTimeCardTransaction.execute();

        PayDayTransaction paydayTransaction = new PayDayTransaction(payDate);
        paydayTransaction.execute();
        
        PayCheck payCheck = paydayTransaction.getPayCheck(employeeId);
        assertThat(payCheck.getPayPeriodEndDate(), is(payDate));
        assertThat(payCheck.getGrossPay(), is(8 * hourlyRate));
        assertThat("hold", is(payCheck.getField("Disposition")));
        assertThat(payCheck.getDeductions(), is(weeklyUnionDues + serviceCharge));
        assertThat(payCheck.getNetPay(), is(8 * hourlyRate - (weeklyUnionDues + serviceCharge)));

	}

	private void validateHourlyPayCheck(PayDayTransaction payDayTransaction, int empId, Calendar payDate, double pay) {
		PayCheck payCheck = payDayTransaction.getPayCheck(empId);
		assertThat(payCheck.getPayPeriodEndDate(), is(payDate));
		assertThat(payCheck.getNetPay(), is(pay));
		assertThat(payCheck.getDeductions(), is(0.0));
		assertThat(payCheck.getGrossPay(), is(pay));
		assertThat(payCheck.getField("Disposition"), is("hold"));
	}


	private void validateCommissionedPayCheck(PayDayTransaction payDayTransaction, int empId, Calendar payDate,
			double expectedGrosspay) {
		// TODO Auto-generated method stub
		PayCheck payCheck = payDayTransaction.getPayCheck(empId);
		assertThat(payCheck.getPayPeriodEndDate(), is(payDate));
		assertThat(payCheck.getNetPay(), is(expectedGrosspay));
		assertThat(payCheck.getDeductions(), is(0.0));
		assertThat(payCheck.getGrossPay(), is(expectedGrosspay));
		assertThat(payCheck.getField("Disposition"), is("hold"));
	}
}
