package services;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;

import Models.Customer;
import dao.CustomerDao;
import dao.FileIO;
import exceptions.UserDoesNotExist;
import logging.Logging;
import menu.Menu;


public class CustomerService {
	
	private CustomerDao cusDao;
	
	public CustomerService(CustomerDao c) {
		this.cusDao = c;
	}
	
	
	
	public Customer signIn(String username, String password) throws UserDoesNotExist {
		
		//System.out.println("Sign in function starting");
		
		Customer c = cusDao.getCustomerByUsername(username);
	//System.out.println(c.toString());
		if(c.getId()==0) {
			Logging.logger.warn("Username does not exist");
			throw new UserDoesNotExist();
		}else if(c.getPassword().equals(password)){
			
			return c;
		}
		System.out.println("Your Password is Incorrect!");
		Menu men = new Menu();
		men.runMenu();
		return null;
		
	}
	
	
	
	
	
	public Customer signUp(String firstName, String lastName, String userName, String password) {
		Customer c = new Customer(firstName, lastName, userName, password);
		
		
		
	 try {
		cusDao.createCustomer(c);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 return c;
	}

	
}
