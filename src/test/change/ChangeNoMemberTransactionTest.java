package test.change;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;

import entities.Employee;
import entities.Affiliations.NoAffiliation;
import entities.Affiliations.UnionAffiliation;
import test.DatabaseResource;
import transcations.add.AddHourlyEmployeeTransaction;
import transcations.change.ChangeUnAffiliationTransaction;

public class ChangeNoMemberTransactionTest {
    @Rule
    public DatabaseResource database = new DatabaseResource();

    @Test
    public void testChangeMemberTransaction() throws Exception {

        int employeeId = 2;
        int memberId = 7734;
        AddHourlyEmployeeTransaction addEmployeeTransaction =
                new AddHourlyEmployeeTransaction(employeeId, "Bill", "Home", 15.25);
        addEmployeeTransaction.execute();

        Employee employee = database.getInstance().getEmployee(employeeId);
        UnionAffiliation unionAffiliation = new UnionAffiliation(memberId,92.1);
        employee.setAffiliation(unionAffiliation);
        assertThat(employee.getAffiliation(), is(unionAffiliation));

        database.getInstance().addUnionMember(memberId, employee);
        assertThat(database.getInstance().getUnionMember(memberId), is(employee));

        ChangeUnAffiliationTransaction noMemberTransaction = new ChangeUnAffiliationTransaction(employeeId);
        noMemberTransaction.execute();

        employee = database.getInstance().getEmployee(employeeId);
        assertThat(employee.getAffiliation(), is(instanceOf(NoAffiliation.class)));

        assertThat(database.getInstance().getUnionMember(memberId), is(nullValue()));
    }
}