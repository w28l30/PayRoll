package test.change;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;

import entities.Employee;
import entities.Affiliations.UnionAffiliation;
import test.DatabaseResource;
import transcations.add.AddEmployeeTransaction;
import transcations.add.AddHourlyEmployeeTransaction;
import transcations.change.ChangeUnionAffiliationTransaction;

public class ChangeMemberTransactionTest {

    @Rule
    public DatabaseResource databaseResource = new DatabaseResource();

    @Test
    public void testChangeMemberTransaction() throws Exception {
        int employeeId = 2;
        int memberId = 7734;
        AddEmployeeTransaction addEmployeeTransaction =
                new AddHourlyEmployeeTransaction(employeeId, "Bill", "Home", 15.25);
        addEmployeeTransaction.execute();

        ChangeUnionAffiliationTransaction changeMemberTransaction =
                new ChangeUnionAffiliationTransaction(employeeId, memberId, 99.42);
        changeMemberTransaction.execute();

        Employee employee = databaseResource.getInstance().getEmployee(employeeId);
        assertThat(employee.getAffiliation(), is(instanceOf(UnionAffiliation.class)));

        UnionAffiliation unionAffiliation = (UnionAffiliation) employee.getAffiliation();
        assertThat(unionAffiliation.getDues(), is(99.42));

        Employee member = databaseResource.getInstance().getUnionMember(memberId);
        assertThat(member, is(employee));
    }
}