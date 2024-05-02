package emp.system;

import java.util.Scanner;

import emp.system.model.Employee;
import emp.system.repo.DataSource;

public class MainApplication {
	private Scanner sc = new Scanner(System.in);
	private DataSource data = new DataSource();

	public static void main(String[] args) {
		System.out.println(":::::::::::::::::::::Welcome to Employee Management System:::::::::::::::::::::");
		new MainApplication().startMainOperations();
	}

	private void startMainOperations() {
		menu();
		do {
			try {
				String opType = sc.nextLine().toUpperCase();
				switch (opType) {
				case "EXIT":
					System.exit(0);
				case "ADD":
					insertEmp();
					break;
				case "VIEW":
					viewEmp();
					break;
				case "VALL":
					viewAllEmp();
					break;
				case "UPDATE":
					updateEmp();
					break;
				case "DEL":
					deleteEmp();
					break;
				case "MENU":
					menu();
					break;
				default:
					System.out.print("************Input is Invalid, Try again************\nPlease check the Menu\n");
				}
			} catch (Exception ex) {
				sc.next();
				System.out.println("Problem Occured Please Insert Integer Only...");
			}
		} while (true);
	}

	private void deleteEmp() {
		System.out.print("Enter Employee ID:");
		try {
			int id = Integer.valueOf(sc.nextLine());
			data.deleteEmployee(id);
		} catch (Exception ex) {
			System.out.println("Invalid Input, Please try again");
		}
	}

	private void updateEmp() {
		try {
			String choice="No";
			System.out.print("Enter Employee ID:");
			int id = Integer.valueOf(sc.nextLine());
			Employee oldEmp=data.fetchEmployee(id);
			Employee newEmp=new Employee();
			System.out.print("Do you want to update name?:");
			choice=sc.nextLine();
			if(choice.equalsIgnoreCase("yes")||choice.equalsIgnoreCase("y")) {
				System.out.print("Enter Name:");
				newEmp.setName(sc.nextLine());
				choice="No";
			}else {
				newEmp.setName(oldEmp.getName());
			}
			
			System.out.print("Do you want to update email?:");
			choice=sc.nextLine();
			if(choice.equalsIgnoreCase("yes")||choice.equalsIgnoreCase("y")) {
				System.out.print("Enter Email:");
				newEmp.setEmail(sc.nextLine());
				choice="No";
			}else {
				newEmp.setEmail(oldEmp.getEmail());
			}
			
			System.out.print("Do you want to update age?:");
			choice=sc.nextLine();
			if(choice.equalsIgnoreCase("yes")||choice.equalsIgnoreCase("y")) {
				System.out.print("Enter Age:");
				try {
				newEmp.setAge(Integer.valueOf(sc.nextLine()));
				choice="No";
				}catch(Exception ex) {
					System.out.println("Invalid Input, try again");
					return;
				}
			}else {
				newEmp.setAge(oldEmp.getAge());
			}
			
			System.out.print("Do you want to update salary?:");
			choice=sc.nextLine();
			if(choice.equalsIgnoreCase("yes")||choice.equalsIgnoreCase("y")) {
				System.out.print("Enter Salary:");
				try {
				newEmp.setSalary(Long.valueOf(sc.nextLine()));
				choice="No";
				}catch(Exception ex) {
					System.out.println("Invalid Input, try again");
					return;
				}
			}else {
				newEmp.setSalary(oldEmp.getSalary());
			}
			
			Employee update=data.updateEmployeeFields(id, newEmp);
			System.out.println("Updated Details:"+update);
		} catch (Exception ex) {
			System.out.println("Invalid Input, Please try again");
		}
	}
	private void viewAllEmp() {
		System.out.println("All Employee Details");
		System.out.println(data.fetchAll());
	}

	private void viewEmp() {
		System.out.print("Enter Employee ID:");
		try {
			int id = Integer.valueOf(sc.nextLine());
			Employee emp = data.fetchEmployee(id);
			if (emp != null)
				System.out.println("Employee Details:" + emp);
		} catch (Exception ex) {
			System.out.println("Invalid Input, Please try again...");
		}
	}

	private void insertEmp() {
		System.out.println("Insert Emp Method");
		Employee emp = new Employee();

		System.out.print("Enter Employee Name:");
		emp.setName(sc.nextLine());
		System.out.print("Enter Email Id:");
		emp.setEmail(sc.nextLine());
		System.out.print("Enter Age:");
		try {
			emp.setAge(Integer.valueOf(sc.nextLine()));
		} catch (Exception ex) {
			System.out.println("Input is invalid, please try again");
			return;
		}
		System.out.print("Enter Salary(float value not allowed:");
		try {
			emp.setSalary(Long.valueOf(sc.nextLine()));
		} catch (Exception ex) {
			System.out.println("Input is invalid, please try again");
			return;
		}
		data.insertEmployee(emp);
	}

	private void menu() {
		System.out.println("Operation:\n" + "Press 'ADD' To Insert a new Employee\n"
				+ "Press 'VIEW' To View the Employee Details\n" + "Press 'VALL' To View All Employees Details\n"
				+ "Press 'UPDATE' To Update Employee Details\n" + "Press 'DEL' To Delete Employee Details\n"
				+ "Press 'MENU' To List Down Menu\n" + "Press 'EXIT' To Exist From System");
	}
}
