package test.change;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;

import entities.Employee;
import test.DatabaseResource;
import transcations.add.AddHourlyEmployeeTransaction;
import transcations.change.ChangeEmployeeAddressTransaction;
import transcations.change.ChangeEmployeeNameTransaction;

public class ChangeNameTransactionTest {

    @Rule
    public DatabaseResource databaseResource = new DatabaseResource();

    @Test
    public void testChangeNameTransaction() throws Exception {
        int employeeId = 2;
        AddHourlyEmployeeTransaction addEmployeeTransaction =
                new AddHourlyEmployeeTransaction(employeeId, "Bill", "Home", 15.25);
        addEmployeeTransaction.execute();

        ChangeEmployeeNameTransaction changeEmployeeName =
                new ChangeEmployeeNameTransaction(employeeId, "Hayley");
        changeEmployeeName.execute();

        Employee employee = databaseResource.getInstance().getEmployee(employeeId);
        assertThat(employee.getName(), is("Hayley"));
    }

}