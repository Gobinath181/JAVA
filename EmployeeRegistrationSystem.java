package Employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class EmployeeRegistrationSystem {
	
	public static String name;
	public static int age;
	public static String address;
	public static int index;
	public static long id;
	
	public static long getId() {
		return id;
	}

	public static void setId(long id) {
		EmployeeRegistrationSystem.id = id;
	}

	public static String getName() {
		return name;
	}

	public void setName(String name) {
		EmployeeRegistrationSystem.name = name;
	}

	public static int getAge() {
		return age;
	}

	public void setAge(int age) {
		EmployeeRegistrationSystem.age = age;
	}

	public static String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		EmployeeRegistrationSystem.address = address;
	}

	public static int getIndex() {
		return index;
	}

	public static void setIndex(int index) {
		EmployeeRegistrationSystem.index = index;
	}

	public static void main(String[] args) {
		String jdbcURL = "jdbc:mysql://localhost:3306/employeeregistrationsystem";
		String username = "root";
		String password = "Password";
		
		System.out.println("Welcome to Employee Management Portal!");
		System.out.println("Press 1 to Add Employee");
		System.out.println("Press 2 to Update Employee");
		System.out.println("Press 3 to Delete Employee");
		System.out.println("Press 4 to Get Employee");
		System.out.print("Please enter your option: ");
		
		Scanner n = new Scanner(System.in);
		index = n.nextInt();
		if(index == 1) {
			Scanner s = new Scanner(System.in);
			System.out.print("Enter Employee Name: ");
			name = s.nextLine();
			System.out.print("Enter Employee Address: ");
			address = s.nextLine();
			System.out.print("Enter Employee Age: ");
			age = s.nextInt();
		
			EmployeeRegistrationSystem m = new EmployeeRegistrationSystem();
			m.setName(name);
			m.setAge(age);
			m.setAddress(address);
		}
		if(index == 2) {
			Scanner s = new Scanner(System.in);
			System.out.print("Enter Employee Id to be updated: ");
			id = s.nextLong();
		}
		
		if(index == 2) {
			Scanner s = new Scanner(System.in);
			System.out.print("Enter Employee Name: ");
			name = s.nextLine();
			System.out.print("Enter Employee Address: ");
			address = s.nextLine();
			System.out.print("Enter Employee Age: ");
			age = s.nextInt();
		
			EmployeeRegistrationSystem m = new EmployeeRegistrationSystem();
			m.setName(name);
			m.setAge(age);
			m.setAddress(address);
		}
		
		
		try {
			Connection connection = DriverManager.getConnection(jdbcURL, username, password);
			
			if (index == 1) {
				String add = "insert into employees(empName,empAge,empAddress)values(?,?,?)";
				
				PreparedStatement statement = connection.prepareStatement(add);
				statement.setString(1, getName());
				statement.setLong(2, getAge());
				statement.setString(3, getAddress());
				int rows = statement.executeUpdate();
				if (rows == 1) {
					System.out.println("New Employee details added succesfully");
				}
			}
			
			if (index == 2) {
				String update = "update employees set empName=?,empAge=?,empAddress=? where empid="+id;
				
				PreparedStatement statement = connection.prepareStatement(update);
				statement.setString(1, getName());
				statement.setLong(2, getAge());
				statement.setString(3, getAddress());
				int rows = statement.executeUpdate();
				
				if(rows > 0) {
					System.out.println("Employee details Updated!");
				}
			}
			
			if(index == 3) {
				Scanner s = new Scanner(System.in);
				System.out.print("Enter Employee Id: ");
				id = s.nextLong();
				
				String delete = "delete from employees where empid="+id;
				
				Statement statement = connection.createStatement();
				int rows = statement.executeUpdate(delete);
				
				if(rows > 0) {
					System.out.println("Employee details has been deleted!");
				}
				
			}
			
			if(index == 4) {
				System.out.println("To get details of a particular Employee, Please enter the employee id or else press 0");
				
				Scanner s = new Scanner(System.in);
				System.out.print("Enter Employee Id: ");
				id = s.nextLong();
				//id=id+10125;
				
				if(id > 10125) {
					String get = "select * from employees where empid="+id;
					
					Statement statement = connection.createStatement();
					ResultSet result = statement.executeQuery(get);
					
					while(result.next()) {
						System.out.println("empId    empName    empAge    empAddress");
						int empId = result.getInt("empId");
						String empName = result.getString("empName");
						int empAge = result.getInt("empAge");
						String empAddress = result.getString("empAddress");
						
						System.out.println(empId+"    "+empName+"    	"+empAge+"    "+empAddress);
					}
				}
				else if(id == 0){
					String get = "select * from employees";
					
					Statement statement = connection.createStatement();
					ResultSet result = statement.executeQuery(get);
					
					while(result.next()) {
						System.out.println("empId    empName    empAge    empAddress");
						int empId = result.getInt("empId");
						String empName = result.getString("empName");
						int empAge = result.getInt("empAge");
						String empAddress = result.getString("empAddress");
						
						System.out.println(empId+"    "+empName+"    	"+empAge+"    "+empAddress);
					}
				}
			}
			connection.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}