package services;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import Models.Customer;
import dao.FileIO;
import logging.Logging;
import menu.Menu;


public class CustomerService {
	
	
	Menu men = new Menu();
	private String file;
	private FileIO<Customer> io;
	
	public CustomerService(String file) {
		this.file = file;
		this.io = new FileIO<Customer>(file);
	}
	
	
	public Customer signUp(String firstName, String lastName, String userName, String password) {
		ArrayList<Customer> cust = null;
		try {
			cust = io.readObjects();
		}catch(FileNotFoundException e) {
			System.out.println("Creating the file to hold");
			cust = new ArrayList<Customer>();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		for(int i =0; i<cust.size(); i++) {
			if(cust.get(i).getUserName()==userName) {
				Logging.logger.warn("Uswername created that already exists");
				System.out.println("This username already exists");
				men.runMenu();
				
			}
		}
		
		Customer cus1 = new Customer(firstName, lastName, userName, password);
		
		cust.add(cus1);
		io.writeObjects(cust);
		return cus1;
	}

}
