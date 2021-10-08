package services;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import Models.Customer;
import dao.CustomerDao;
import dao.FileIO;
import logging.Logging;
import menu.Menu;


public class CustomerService {
	
	private CustomerDao cusDao;
	
	public CustomerService(CustomerDao c) {
		this.cusDao = c;
	}
	
}
