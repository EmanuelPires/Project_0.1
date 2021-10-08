package dao;

import java.sql.SQLException;
import java.util.List;

import Models.Customer;

public interface CustomerDao {

	List<Customer>getAllCustomers();
	
	Customer getCustomerByUsername(String userName);
	
	void createCustomer(Customer c) throws SQLException;
	
	void updateCustomer(Customer c) throws SQLException;
	
	void deleteustomer(Customer c ) throws SQLException;
	
	
}
