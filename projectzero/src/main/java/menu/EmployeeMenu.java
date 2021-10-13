package menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Models.Customer;
import dao.CustomerDao;
import dao.CustomerDaoDB;

public class EmployeeMenu {
	
	
	
	public static void EmpMenu1(Customer e) {
		
		CustomerDaoDB empDao=new CustomerDaoDB(); 
		
		List<Customer> unAppedAccts = new ArrayList<Customer>();
		
		System.out.println("Welcome to the employee menu " + e.getFirstName());
		
		Scanner in = new Scanner(System.in);
		
		
		System.out.println("What would you like to do?");
		System.out.println("1 Approve Accounts");
		System.out.println("2 Access Account");
		System.out.println("3 Exit");
		
		String res= in.nextLine();
        switch(res) {
        case "1": 
        	unAppedAccts = empDao.getUnapprovedCusts(e);
        	
        	
        	
        	
        	for(int i =0; i<unAppedAccts.size();i++) {
        		
        		System.out.println("Would you like to approve the following acount? Y, N or Cancel");
        		System.out.println(unAppedAccts.get(i));
        		
        	Customer curCust = unAppedAccts.get(i);
        		
        		String verdict = in.nextLine();
        		
        		if(verdict.equals("Y")) {
        			System.out.println("Thank you this account is approved");
        			empDao.approvingAccounts(curCust);
        			
        		}else if(verdict.equals("Cancel")) {
        			Menu men = new Menu();
        			men.runMenu();
        		}else if (verdict.equals("N")) {
        			continue;
        		}
        	}
        	
        	System.out.println("No more accounts to approve");
        	EmployeeMenu.EmpMenu1(e);
        	
        case "2":
        	System.out.println("Lets get accounts");
        case "3":
        	System.out.println("Goodbye");
        	Menu men = new Menu();
        	men.runMenu();
        }

	}

}
