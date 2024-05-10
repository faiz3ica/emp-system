package emp.system.mapper;

import emp.system.model.Employee;

public class EmployeeMapper {
    
    private static EmployeeMapper mapper=null;
    
    protected EmployeeMapper() {}
    
    public static EmployeeMapper getInstance() {
	if(mapper==null) {
	    mapper=new EmployeeMapper();
	}
	return mapper;
    }
    public Employee converIntoEmployee(String source) {
	    Employee target=new Employee();
	    String[] values=source.replace("[", "").replace("]","").split(",");
	    target.setName(values[0]);
	    target.setAge(Integer.valueOf(values[1]));
	    target.setEmail(values[2]);
	    target.setSalary(Long.valueOf(values[3]));
	    return target;
	}
}
