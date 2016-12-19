package test.add;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Rule;
import org.junit.Test;

import entities.Employee;
import entities.ServiceCharge;
import entities.Affiliations.UnionAffiliation;
import test.DatabaseResource;
import transcations.Transaction;
import transcations.add.AddHourlyEmployeeTransaction;
import transcations.add.AddServiceChargeTransaction;

public class AddServiceChargeTransactionTest {

	@Rule
	public DatabaseResource database = new DatabaseResource();

	@Test
	public void testAddServiceCharge() throws Exception {
		int employeeId = 2;
		Transaction addEmployeeTransaction = new AddHourlyEmployeeTransaction(employeeId, "Bill", "Home", 15.25);
		addEmployeeTransaction.execute();

		Employee employee = database.getInstance().getEmployee(employeeId);
		assertNotNull(employee);

		int memberId = 86;
		UnionAffiliation unionAffiliation = new UnionAffiliation(memberId, 12.5);
		employee.setAffiliation(unionAffiliation);
		database.getInstance().addUnionMember(memberId, employee);
		assertNotNull(database.getInstance().getUnionMember(memberId));

		Calendar date = new GregorianCalendar(2001, 11, 01);
		AddServiceChargeTransaction addServiceChargeTransaction = new AddServiceChargeTransaction(memberId, date,
				12.95);
		addServiceChargeTransaction.execute();
		ServiceCharge serviceCharge = unionAffiliation.getServiceCharge(date);
		assertThat(serviceCharge, is(notNullValue()));
		assertThat(serviceCharge.getAmount(), is(12.95));
	}
}