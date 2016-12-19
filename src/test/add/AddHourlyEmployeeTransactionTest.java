package test.add;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;

import entities.Employee;
import entities.PaymentClassifications.HourlyClassification;
import entities.PaymentClassifications.PaymentClassification;
import entities.paymentMethods.HoldMethod;
import entities.paymentSchedule.WeeklySchedule;
import test.DatabaseResource;
import transcations.Transaction;
import transcations.add.AddHourlyEmployeeTransaction;

public class AddHourlyEmployeeTransactionTest {
	@Rule
	public DatabaseResource database = new DatabaseResource();
	
	@Test
	public void testAddHourlyEmployee() {
        int employeeId = 1;
        Transaction addEmployeeTransaction =
                new AddHourlyEmployeeTransaction(employeeId, "Steve", "Home", 20.0);
        addEmployeeTransaction.execute();
        Employee employee = database.getInstance().getEmployee(employeeId);
        assertThat(employee.getName(), is("Steve"));

        PaymentClassification paymentClassification = employee.getClassification();
        assertThat(paymentClassification, is(instanceOf(HourlyClassification.class)));
        HourlyClassification hourlyPaymentClassification =
                (HourlyClassification) paymentClassification;
        assertThat(hourlyPaymentClassification.getHourlyRate(), is(20.0));

        assertThat(employee.getPaymentSchedule(), is(instanceOf(WeeklySchedule.class)));
        assertThat(employee.getMethod(), is(instanceOf(HoldMethod.class)));
	}

}
