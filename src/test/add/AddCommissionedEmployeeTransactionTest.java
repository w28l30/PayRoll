package test.add;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;

import entities.Employee;
import entities.PaymentClassifications.CommissionClassification;
import entities.PaymentClassifications.PaymentClassification;
import entities.paymentMethods.HoldMethod;
import entities.paymentSchedule.BiWeeklySchedule;
import test.DatabaseResource;
import transcations.Transaction;
import transcations.add.AddCommissionedEmployeeTransaction;

public class AddCommissionedEmployeeTransactionTest {
	@Rule
    public DatabaseResource database = new DatabaseResource();

    @Test
    public void testAddCommissionedEmployee(){
        int employeeId = 1;
        Transaction addEmployeeTransaction =
                new AddCommissionedEmployeeTransaction(employeeId, "Michael", "Home", 20.0, 200.0);
        addEmployeeTransaction.execute();
        Employee employee = database.getInstance().getEmployee(employeeId);
        assertThat(employee.getName(), is("Michael"));

        PaymentClassification paymentClassification = employee.getClassification();
        assertThat(paymentClassification, is(instanceOf(CommissionClassification.class)));
        CommissionClassification commissionedClassification =
                (CommissionClassification) paymentClassification;
        assertThat(commissionedClassification.getCommissionRate(), is(20.0));
        assertThat(commissionedClassification.getMonthlySalary(), is(200.0));

        assertThat(employee.getPaymentSchedule(), is(instanceOf(BiWeeklySchedule.class)));
        assertThat(employee.getMethod(), is(instanceOf(HoldMethod.class)));
    }

}
