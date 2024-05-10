package emp.system;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

import emp.system.mapper.EmployeeMapper;
import emp.system.model.Employee;
import emp.system.repo.DataSource;

public class MainApplication {
    private Logger logger = Logger.getLogger("MainApplication"); 
    private Scanner sc = new Scanner(System.in);
    private DataSource data = new DataSource();
    private EmployeeMapper mapper=EmployeeMapper.getInstance();
    private String fileLocation = "D:\\Employee";

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
		case "IMPORT":
		    dataImport();
		    break;
		case "EXPORT":
		    dataExport();
		    break;
		case "COUNT":
		    System.out.println(count());
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

    private void dataExport() {
	logger.info("Data Export Method Started");
	System.out.print("Enter File Name:");
	String fileName = sc.nextLine();
	System.out.println("Default File Location Is : " + fileLocation);
	System.out.print("Do you want to update?");
	String choice = sc.nextLine();
	if (choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y")) {
	    System.out.print("Enter New File Location:");
	    fileLocation = sc.nextLine();
	}
	File file = new File(fileLocation);
	if (!file.isDirectory()) {
	    System.out.println("Directory Created");
	    file.mkdirs();
	}
	file = new File(fileLocation + "\\" + fileName + ".txt");
	if (!file.exists()) {
	    try {
		boolean check = file.createNewFile();
		if (check)
		    System.out.println("File Created...");
		else
		    System.out.println("File Not Created...");
	    } catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();

	    }
	}
	try (FileWriter writer = new FileWriter(file)) {
	    data.fetchAll().forEach((key, value) -> {
		try {
		    writer.write(key + "\t" + value + "\n");
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    });
	    System.out.println("*****Data Exported*****");
	} catch (IOException e) {
	    e.printStackTrace();
	} finally {
	    System.out.println("File Path:" + file.getPath());
	    logger.info("Data Export Method Completed");
	}
    }

    private void dataImport() {
	// TODO Auto-generated method stub
	System.out.println("It will remove all your current application data");
	System.out.print("Do you want to remove?(Yes):");
	String choice = sc.nextLine();
	if (choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y"))
	    data = new DataSource();
	else
	    return;
	System.out.print("Enter file Path:");
	String filePath = sc.nextLine();
	File file = new File(filePath);
	if (file.isFile()) {
	    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
		String line;
		while ((line = reader.readLine()) != null) {
		    String[] emp = line.split("\t");
		    data.insertEmployee(Integer.valueOf(emp[0]), mapper.converIntoEmployee(emp[1]));
		}
		if (data.fetchAll().size() != 0) {
		    System.out.println("Data Imported");
		}
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	} else {
	    System.out.println("File Not Found | Invalid File Path");
	}

    }

    private String count() {
	// TODO Auto-generated method stub
	return "Total Employee: " + data.fetchAll().size();
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
	    String choice = "No";
	    System.out.print("Enter Employee ID:");
	    int id = Integer.valueOf(sc.nextLine());
	    Employee oldEmp = data.fetchEmployee(id);
	    Employee newEmp = new Employee();
	    System.out.print("Do you want to update name?:");
	    choice = sc.nextLine();
	    if (choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y")) {
		System.out.print("Enter Name:");
		newEmp.setName(sc.nextLine());
		choice = "No";
	    } else {
		newEmp.setName(oldEmp.getName());
	    }

	    System.out.print("Do you want to update email?:");
	    choice = sc.nextLine();
	    if (choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y")) {
		System.out.print("Enter Email:");
		newEmp.setEmail(sc.nextLine());
		choice = "No";
	    } else {
		newEmp.setEmail(oldEmp.getEmail());
	    }

	    System.out.print("Do you want to update age?:");
	    choice = sc.nextLine();
	    if (choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y")) {
		System.out.print("Enter Age:");
		try {
		    newEmp.setAge(Integer.valueOf(sc.nextLine()));
		    choice = "No";
		} catch (Exception ex) {
		    System.out.println("Invalid Input, try again");
		    return;
		}
	    } else {
		newEmp.setAge(oldEmp.getAge());
	    }

	    System.out.print("Do you want to update salary?:");
	    choice = sc.nextLine();
	    if (choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y")) {
		System.out.print("Enter Salary:");
		try {
		    newEmp.setSalary(Long.valueOf(sc.nextLine()));
		    choice = "No";
		} catch (Exception ex) {
		    System.out.println("Invalid Input, try again");
		    return;
		}
	    } else {
		newEmp.setSalary(oldEmp.getSalary());
	    }

	    Employee update = data.updateEmployeeFields(id, newEmp);
	    System.out.println("Updated Details:" + update);
	} catch (Exception ex) {
	    System.out.println("Invalid Input, Please try again");
	}
    }

    private void viewAllEmp() {
	System.out.println("All Employee Details");
	data.fetchAll().forEach((key, value) -> {
	    System.out.println("*******************************************************************");
	    System.out.println("Employee ID:" + key);
	    System.out.println("Employee Name:" + value.getName());
	    System.out.println("Employee Age:" + value.getAge());
	    System.out.println("Employee Email:" + value.getEmail());
	    System.out.println("Employee Salary:" + value.getSalary());
	    System.out.println("*******************************************************************");
	});
	System.out.println();
    }

    private void viewEmp() {
	System.out.print("Enter Employee ID:");
	try {
	    int id = Integer.valueOf(sc.nextLine());
	    Employee emp = data.fetchEmployee(id);
	    System.out.println("Employee Details for ID: " + id);
	    System.out.println("*******************************************************************");
	    System.out.println("Employee Name:" + emp.getName());
	    System.out.println("Employee Age:" + emp.getAge());
	    System.out.println("Employee Email:" + emp.getEmail());
	    System.out.println("Employee Salary:" + emp.getSalary());
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
		+ "Command 'UPDATE' - To Update Employee Details\n" + "Command 'COUNT' - To Count Employee Details\n"
		+ "Command 'DEL' - To Delete Employee Details\n" + "Command 'MENU' - To List Down Operations\n"
		+ "Command 'IMPORT' - To Import Employee Details\n" + "Command 'EXPORT' - To Export Employee Details\n"
		+ "Command 'EXIT' - To Exist From System");
    }
}
