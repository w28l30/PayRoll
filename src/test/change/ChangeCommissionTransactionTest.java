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

public class ChangeCommissionTransactionTest {

    @Rule
    public DatabaseResource databaseResource = new DatabaseResource();

    @Test
    public void testChangeHourlyTransaction() throws Exception {
        int employeeId = 3;
        AddEmployeeTransaction addEmployeeTransaction =
                new AddCommissionedEmployeeTransaction(employeeId, "Lance", "Home", 2500, 3.2);
        addEmployeeTransaction.execute();

        ChangeSalariedClassification changeSalariedClassification = new ChangeSalariedClassification(employeeId, 3000.0);
        changeSalariedClassification.execute();

        Employee employee = databaseResource.getInstance().getEmployee(employeeId);
        assertThat(employee.getClassification(), is(instanceOf(SalariedClassification.class)));
        SalariedClassification paymentClassification =
                (SalariedClassification) employee.getClassification();
        assertThat(paymentClassification.getSalary(), is(3000.0));
        assertThat(employee.getPaymentSchedule(), is(instanceOf(MonthlySchedule.class)));
    }
}