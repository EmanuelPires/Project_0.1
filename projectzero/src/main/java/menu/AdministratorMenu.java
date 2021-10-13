package menu;

import java.sql.SQLException;
import java.util.Scanner;

import Models.Customer;
import dao.CustomerDao;
import dao.CustomerDaoDB;

public class AdministratorMenu {
	
	public static void adminMenu(Customer c) {
		System.out.println("Welcome " + c.getFirstName());
		System.out.println("What would you like to do? Pick a number");
		System.out.println("1 Approve Accounts");
		System.out.println("2 Cancel Accounts");
		System.out.println("3 Deposit");
		System.out.println("4 Withdraw");
		System.out.println("5 Transfer");;
		
		
		Scanner in  = new Scanner(System.in);
		String choice = in.nextLine();
		
		
		switch(choice) {
		case "1":
System.out.println("Lets Approve accounts");
		case "2":
			System.out.println("Lets cancel accounts");
		case "3":
			System.out.println("Enter the Customer's ID# where you'd like to deposit");
			int cusId1 = in.nextInt();
		case "4":
			System.out.println("Enter the customer's ID# where you'd like to withdraw");
			cusId1 = in.nextInt();
		case "5":
		System.out.println("Enter the first customer ID of whom you want to steal from");
		cusId1 = in.nextInt();
		System.out.println("Enter the lucky customer who gets free money");
		int cusId2 = in.nextInt();
		System.out.println("Enter the amount to transfer");
		float tranAmount = in.nextFloat();
		CustomerDaoDB cusDao = new CustomerDaoDB();
			try {
				cusDao.adminTransfer(cusId1, cusId2, tranAmount);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		
	}

}
