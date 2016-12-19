package test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Rule;
import org.junit.Test;

import datasources.PayRollDatabase;
import entities.Employee;
import entities.TimeCard;
import entities.PaymentClassifications.HourlyClassification;
import entities.PaymentClassifications.PaymentClassification;
import transcations.Transaction;
import transcations.add.AddHourlyEmployeeTransaction;
import transcations.add.AddTimeCardTransaction;

public class TestAddTimeCardTransaction {
	@Rule
	public DatabaseResource database = new DatabaseResource();
	
	@Test
	public void testTimeCardTransaction() {
        int employeeId = 2;
        AddHourlyEmployeeTransaction addHourlyEmployee =
                new AddHourlyEmployeeTransaction(employeeId, "Billy", "Home", 15.25);
        addHourlyEmployee.execute();

        Calendar date = new GregorianCalendar(2001,10,31);
        Transaction timeCardTransaction = new AddTimeCardTransaction(date, 8.0, employeeId);
        timeCardTransaction.execute();
        
        Employee employee = database.getInstance().getEmployee(employeeId);
        assertThat(employee, is(notNullValue()));
        
        PaymentClassification paymentClassification = employee.getClassification();
        HourlyClassification hourlyPaymentClassification = (HourlyClassification) paymentClassification;
        assertThat(hourlyPaymentClassification, is(notNullValue()));

        TimeCard timeCard =  hourlyPaymentClassification.getTimeCard(date);
        assertNotNull(timeCard);
        assertThat(timeCard.getHours() , is(8.0));
	}
	
}
