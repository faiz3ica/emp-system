package emp.system.model;

import java.util.Objects;

public class Employee {
	private String name;
	private int age;
	private String email;
	private long salary;
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employee(String name, int age, String email, long salary) {
		super();
		this.name = name;
		this.age = age;
		this.email = email;
		this.salary = salary;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getSalary() {
		return salary;
	}
	public void setSalary(long salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "["+ name + "," + age + "," + email + "," + salary + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(age, email, name, salary);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return age == other.age && Objects.equals(email, other.email) && Objects.equals(name, other.name)
				&& salary == other.salary;
	}
	
	
}
