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
			System.out.print("Enter Command:");
			try {
				String opType = sc.nextLine().toUpperCase();
				switch (opType) {
				case "EXIT":
					System.exit(0);
				case "ADD":
					insertEmp();
					break;
				case "ADDALL":
					addInBulk();
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
		data.fetchAll().forEach((key,value)->{
			System.out.println("*******************************************************************");
			System.out.println("Employee ID:"+key);
			System.out.println("Employee Name:"+value.getName());
			System.out.println("Employee Age:"+value.getAge());
			System.out.println("Employee Email:"+value.getEmail());
			System.out.println("Employee Salary:"+value.getSalary());
			System.out.println("*******************************************************************");
		});
		System.out.println();
	}

	private void viewEmp() {
		System.out.print("Enter Employee ID:");
		try {
			int id = Integer.valueOf(sc.nextLine());
			Employee emp = data.fetchEmployee(id);
			System.out.println("Employee Details for ID: "+id);
			System.out.println("*******************************************************************");
			System.out.println("Employee Name:"+emp.getName());
			System.out.println("Employee Age:"+emp.getAge());
			System.out.println("Employee Email:"+emp.getEmail());
			System.out.println("Employee Salary:"+emp.getSalary());
			System.out.println("*******************************************************************");
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
		System.out.print("Enter Age (*only Integer):");
		try {
			emp.setAge(Integer.valueOf(sc.nextLine()));
		} catch (Exception ex) {
			System.out.println("Input is invalid, please try again");
			return;
		}
		System.out.print("Enter Salary(*only Integer):");
		try {
			emp.setSalary(Long.valueOf(sc.nextLine()));
		} catch (Exception ex) {
			System.out.println("Input is invalid, please try again");
			return;
		}
		data.insertEmployee(emp);
	}
	
	private void addInBulk() {
		
	}

	private void menu() {
		System.out.println("Operation:\n" + "Command 'ADD' - To Insert a new Employee\n"
				+ "Command 'VIEW' - To View the Employee Details\n" + "Command 'VALL' - To View All Employees Details\n"
				+ "Command 'UPDATE' - To Update Employee Details\n" + "Command 'DEL' - To Delete Employee Details\n"
				+ "Command 'MENU' - To List Down Operations\n" + "Command 'EXIT' - To Exist From System");
	}
}
