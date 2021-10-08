import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Models.Customer;
import dao.CustomerDao;
import dao.CustomerDaoDB;
import menu.Menu;
import services.CustomerService;

public class BankDriver {
	private static CustomerDao cusDao = new CustomerDaoDB();
	private static CustomerService cusServ = new CustomerService(cusDao);
	
	
	
	public static void main(String[] args) {
		
	 System.out.println( cusDao.getAllCustomers())	;
		
		//System.out.println(cusList);
		Menu men = new Menu();
		men.runMenu();
		

	}

}
