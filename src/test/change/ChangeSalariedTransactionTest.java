package test.change;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;

import entities.Employee;
import entities.PaymentClassifications.CommissionClassification;
import entities.PaymentClassifications.HourlyClassification;
import entities.PaymentClassifications.SalariedClassification;
import entities.paymentSchedule.BiWeeklySchedule;
import entities.paymentSchedule.MonthlySchedule;
import entities.paymentSchedule.WeeklySchedule;
import test.DatabaseResource;
import transcations.add.AddCommissionedEmployeeTransaction;
import transcations.add.AddEmployeeTransaction;
import transcations.add.AddHourlyEmployeeTransaction;
import transcations.change.ChangeClassificationTransaction;
import transcations.change.ChangeCommissionClassification;
import transcations.change.ChangeHourlyClassification;
import transcations.change.ChangeSalariedClassification;

public class ChangeSalariedTransactionTest {

    @Rule
    public DatabaseResource databaseResource = new DatabaseResource();

    @Test
    public void testChangeHourlyTransaction() throws Exception {
        int employeeId = 3;
        AddEmployeeTransaction addEmployeeTransaction =
                new AddHourlyEmployeeTransaction(employeeId, "Bill", "Home", 30.25);
        addEmployeeTransaction.execute();

        ChangeCommissionClassification changeCommissionClassification = new ChangeCommissionClassification(employeeId, 3000.0, 42.55);
        changeCommissionClassification.execute();

        Employee employee = databaseResource.getInstance().getEmployee(employeeId);
        assertThat(employee.getClassification(), is(instanceOf(CommissionClassification.class)));
        CommissionClassification paymentClassification =
                (CommissionClassification) employee.getClassification();
        assertThat(paymentClassification.getMonthlySalary(), is(3000.0));
        assertThat(paymentClassification.getCommissionRate(), is(42.55));
        assertThat(employee.getPaymentSchedule(), is(instanceOf(BiWeeklySchedule.class)));
    }
}