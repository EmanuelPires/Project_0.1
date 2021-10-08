package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Models.Customer;
import utils.ConnectionUtil;

public class CustomerDaoDB implements CustomerDao {
	
	
	ConnectionUtil conUtil =  ConnectionUtil.getConnectionUtil();

	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		
		
		List<Customer> cusList = new ArrayList<Customer>();
		
		try {
			Connection con = conUtil.getConnection();
			
			String sql = "SELECT * from bankuser";
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			
			while(rs.next()) {
			cusList.add( new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getBoolean(7)));
			}
			
			return cusList;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Customer getCustomerByUsername(String userName) {
		// TODO Auto-generated method stub
		
		
	Connection con = conUtil.getConnection();
	String sql = "SELECT * bankuser WHERE first_name=" + userName;
	try {
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery(sql);
		Customer c1 = new Customer();
		c1.setId(rs.getInt(1));
		c1.setFirstName(rs.getString(2));
		c1.setLastName(rs.getString(3));
		c1.setUserName(rs.getString(4));
		c1.setPassword(rs.getString(5));
		c1.setUser_type(rs.getString(0));
		
		
		//finish up the creation of the user that we're getting back on sign in
		
		return c1;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.print("I know that its not suppsed to say \n your username is wrong but thats what it is");
		e.printStackTrace();
	}
	
		
		
		
		return null;
	}

	@Override
	public void createCustomer(Customer c) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCustomer(Customer c) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteustomer(Customer c) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
