package test.change;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;

import entities.Employee;
import entities.PaymentClassifications.HourlyClassification;
import entities.PaymentClassifications.SalariedClassification;
import entities.paymentSchedule.MonthlySchedule;
import entities.paymentSchedule.WeeklySchedule;
import test.DatabaseResource;
import transcations.add.AddCommissionedEmployeeTransaction;
import transcations.add.AddEmployeeTransaction;
import transcations.change.ChangeHourlyClassification;
import transcations.change.ChangeSalariedClassification;

public class ChangeHourlyTransactionTest {

    @Rule
    public DatabaseResource databaseResource = new DatabaseResource();

    @Test
    public void testChangeHourlyTransaction() throws Exception {
        int employeeId = 3;
        AddEmployeeTransaction addEmployeeTransaction =
                new AddCommissionedEmployeeTransaction(employeeId, "Lance", "Home", 2500, 3.2);
        addEmployeeTransaction.execute();

        ChangeHourlyClassification changeHourlyTransaction = new ChangeHourlyClassification(employeeId, 27.52);
        changeHourlyTransaction.execute();

        Employee employee = databaseResource.getInstance().getEmployee(employeeId);
        assertThat(employee.getClassification(), is(instanceOf(HourlyClassification.class)));
        HourlyClassification paymentClassification =
                (HourlyClassification) employee.getClassification();
        assertThat(paymentClassification.getHourlyRate(), is(27.52));
        assertThat(employee.getPaymentSchedule(), is(instanceOf(WeeklySchedule.class)));
    }
}