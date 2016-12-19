package datasources;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import entities.Employee;

public class PayRollDatabase {
	public static PayRollDatabase newInstance = new PayRollDatabase();
	
	public static PayRollDatabase getInstance() {
		return newInstance;
	}
	
	private static Map<Integer, Employee> employees = new HashMap<>();
	private static Map<Integer, Employee> unionMembers = new HashMap<>();
	
	public void addEmployee(int id, Employee e) {
		employees.put(id, e);
	}
	
	public Employee getEmployee(int id) {
		return employees.get(id);
	}
	
	public void deleteEmployee(int id) {
		employees.remove(id);
	}
	
	public void addUnionMember(int memberId, Employee employee) {
		unionMembers.put(memberId, employee);
	}
	
	public Employee getUnionMember(int memberId) {
		return unionMembers.get(memberId);
	}
	
	public void deleteUnionMember(int memberId) {
		unionMembers.remove(memberId);
	}
	
	public Set<Integer> getAllEmployees() {
		return employees.keySet();
	}
	
	public void clear() {
		employees.clear();
	}
}
