package menu;

import java.util.Scanner;

import Models.Customer;
import dao.CustomerDao;
import dao.CustomerDaoDB;
import services.CustomerService;

public class Menu {
	
	private static CustomerDao cusDao = new CustomerDaoDB();
	private static CustomerService cusServ = new CustomerService(cusDao);
	
	//private static CustomerService cusServ = new CustomerService());
	
	public void runMenu() {
		System.out.println("Welcome to the Bank App");
		System.out.println("What would you like to do?");
		System.out.println("Press 1 to Signup");
		System.out.println("Press 2 to Login");
		
		Scanner in = new Scanner(System.in);
		
		
		String choice = in.nextLine();
		
		
		Customer cus = null;
		
		switch(choice) {
		case "1":
			System.out.println("Enter your first name");
			String firstName = in.nextLine();
			System.out.println("Enter your last name");
			String lastName = in.nextLine();
			System.out.println("Enter your username");
			String userName = in.nextLine();
			System.out.println("Enter your password");
			String password = in.nextLine();
			cus = cusServ.signUp(firstName, lastName, userName, password);
			CustomerMenu.cusMenu2();

	   
		
		break;
		
		case "2":
			System.out.println("Enter your Username");
			String username = in.nextLine();
			System.out.println("Enter your Password");
		    password = in.nextLine();
		    
		    cus= cusServ.signIn(username, password);
		    
		   
		   
		   if(cus.getUser_type().equals("customer")) {
			   
			   CustomerMenu.cusMenu1(cus);  
		   }else if(cus.getUser_type().equals("employee")) {
			   //System.out.println("We made it to the employee menu");
			   EmployeeMenu.EmpMenu1(cus);
		   }
		   
		   
		     
		break;     
		    
			
		default: System.out.println("You entereed something wrong");
		
			
			
		}
	}

}
