package test.add;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;

import entities.Employee;
import entities.Affiliations.NoAffiliation;
import entities.PaymentClassifications.PaymentClassification;
import entities.PaymentClassifications.SalariedClassification;
import entities.paymentMethods.HoldMethod;
import entities.paymentSchedule.MonthlySchedule;
import test.DatabaseResource;
import transcations.Transaction;
import transcations.add.AddSalariedEmployeeTransaction;

public class AddSalariedEmployeeTransactionTest {

    @Rule
    public DatabaseResource databaseResource = new DatabaseResource();

    @Test
    public void testAddSalariedEmployee(){
        int employeeId = 1;
        Transaction addEmployeeTransaction =
                new AddSalariedEmployeeTransaction(employeeId, "Bob", "Home", 1000.0);
        addEmployeeTransaction.execute();

        Employee employee = databaseResource.getInstance().getEmployee(employeeId);
        assertThat(employee.getName(), is("Bob"));

        PaymentClassification paymentClassification = employee.getClassification();
        assertThat(paymentClassification, is(instanceOf(SalariedClassification.class)));
        SalariedClassification salariedClassification = (SalariedClassification) paymentClassification;
        assertThat(salariedClassification.getSalary(), is(1000.0));

        assertThat(employee.getPaymentSchedule(), is(instanceOf(MonthlySchedule.class)));
        assertThat(employee.getMethod(), is(instanceOf(HoldMethod.class)));
        assertThat(employee.getAffiliation(), is(instanceOf(NoAffiliation.class)));
    }

}