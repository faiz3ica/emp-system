package emp.system.repo;

import java.util.HashMap;
import java.util.Map;

import emp.system.model.Employee;

public class DataSource {
	private Map<Integer,Employee> dataSource=new HashMap<>();
	
	public void insertEmployee(Employee employee) {
		System.out.println("Insertion Started...");
		Integer empId=dataSource.size()+1;
		dataSource.put(empId,employee);
		System.out.println("********** Data Inserted Successfully **********");
		System.out.println("Employee id is:"+empId);
		System.out.println("Employee Details:"+employee);
		System.out.println("Insertion Completed...");
	}
	
	public void insertEmployee(int id,Employee employee) {
		System.out.println("Insertion Started...");
		dataSource.put(id,employee);
		System.out.println("********** Data Inserted Successfully **********");
		System.out.println("Employee id is:"+id);
		System.out.println("Insertion Completed...");
	}
	
	public Employee fetchEmployee(Integer empId) {
		System.out.println("Fetching Started...");
		if(dataSource.containsKey(empId)) {
			Employee emp=dataSource.get(empId);
			System.out.println("Employee Details:"+emp);
			System.out.println("Fetching Completed...");
			return emp;
		}
		System.out.println("Employee id is invalid");
		return null;
	}
	
	public Map<Integer,Employee> fetchAll(){
		return this.dataSource;
	}
	
	public Employee updateEmployeeFields(Integer id,Employee emp) {
		if(dataSource.containsKey(id)) {
			Employee oldEmpDetails=dataSource.get(id);
			System.out.println("Old Emp Details:"+oldEmpDetails);
			dataSource.put(id, emp);
			System.out.println("Fetching Completed...");
			return emp;
		}else {
			System.out.println("Employee Id is invalid");
			return null;
		}
	}
	
	public void deleteEmployee(Integer empId) {
		System.out.println("Deletion Started...");
		if(dataSource.containsKey(empId)) {
			Employee emp=dataSource.get(empId);
			dataSource.remove(empId);
			System.out.println("Removed Employee Details For "+empId+"-ID");
			System.out.println(emp);
		}else {
			System.out.println("Employee Id is Invalid");
		}
		System.out.println("Deletion Completed...");
		
	}
}
