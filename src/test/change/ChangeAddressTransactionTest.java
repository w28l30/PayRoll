package test.change;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;

import entities.Employee;
import test.DatabaseResource;
import transcations.add.AddHourlyEmployeeTransaction;
import transcations.change.ChangeEmployeeAddressTransaction;

public class ChangeAddressTransactionTest {

    @Rule
    public DatabaseResource databaseResource = new DatabaseResource();

    @Test
    public void testChangeNameTransaction() throws Exception {
        int employeeId = 2;
        AddHourlyEmployeeTransaction addEmployeeTransaction =
                new AddHourlyEmployeeTransaction(employeeId, "Bill", "Home", 15.25);
        addEmployeeTransaction.execute();

        ChangeEmployeeAddressTransaction changeEmployeeAddressTransaction =
                new ChangeEmployeeAddressTransaction(employeeId, "Mars");
        changeEmployeeAddressTransaction.execute();

        Employee employee = databaseResource.getInstance().getEmployee(employeeId);
        assertThat(employee.getAddress(), is("Mars"));
    }

}