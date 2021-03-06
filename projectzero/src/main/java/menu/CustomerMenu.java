package menu;

import java.sql.SQLException;
import java.util.Scanner;

import Models.AccountInfo;
import Models.Customer;
import dao.CustomerDaoDB;

public class CustomerMenu {

	
	
	public static void cusMenu1(Customer c){
		
		
	
	System.out.println("Welcome to your account " + c.getFirstName());
	if(c.isAccnt_aproved()==false) {
		System.out.println("Your account is not approved yet try again later");
		
		Menu men  = new Menu();
		men.runMenu();
		
	}
	
	
	
	System.out.println("What would you like to do ?");
	System.out.println("1 Deposit");
	System.out.println("2 Withdraw");
	System.out.println("3 Transfer");
	System.out.println("4 Logout");
	CustomerMenu.cusMenu3(c);

	
	}
	
	
	
	
	//---------------------------------------------------------------------------------------------------------
	
	
	
	public static void cusMenu2() {
		System.out.println("Your account is being approved try signing in later");
		
		Menu men  = new Menu();
		men.runMenu();
	}
	
	
	
	//---------------------------------------------------------------------------------------------------------
	
	
	
	
	public static void cusMenu3(Customer c) {
		
CustomerDaoDB cusDao = new CustomerDaoDB();
		
		AccountInfo act = new AccountInfo();
		Scanner in = new Scanner(System.in);
		String choice = in.nextLine();
			switch(choice) {
			case "1":
				
				System.out.println("How much would you like to deposit");
				
				String amount = in.nextLine();
				
				float amnt = Float.parseFloat(amount);
				try {
					act = cusDao.deposit(c, amnt);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("Thank you for your deposit");
			System.out.println(act.toString());
			CustomerMenu.cusMenu1(c);
			
			
			
			case "2":
				System.out.println("How much would you like to withdraw?");
				amount = in.nextLine();
				amnt = Float.parseFloat(amount);
				try {
					act = cusDao.withdraw(c, amnt);
				}catch(SQLException e) {
					e.printStackTrace();
				}
				System.out.println("Funds withdrawn");
				System.out.println(act.toString());
				CustomerMenu.cusMenu1(c);
				
			case "3":
				// Maake Transfer
				System.out.println("Enter the Custoer ID number of the person you want to transfer to:");
				int cusId = in.nextInt();
				System.out.println("Enter the amount you want to transfer");
				float tranAmount = in.nextFloat();
				System.out.println("Your funds are being transfered");
				try {
					cusDao.transfer(c, cusId, tranAmount);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				CustomerMenu.cusMenu1(c);
			case "4":
				
				System.out.println("Goodbye!");
			  	Menu men = new Menu();
			  	men.runMenu();
			  	
			 default:
				 System.out.println("Thats not an option try again");
				 CustomerMenu.cusMenu1(c);
			}
	}

}
