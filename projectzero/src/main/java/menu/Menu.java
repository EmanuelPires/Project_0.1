package menu;

import java.util.Scanner;

import Models.Customer;
import services.CustomerService;

public class Menu {
	
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
			
	
	
		
		break;
		
		default: System.out.println("You entereed something wrong");
		
			
			
		}
	}

}
