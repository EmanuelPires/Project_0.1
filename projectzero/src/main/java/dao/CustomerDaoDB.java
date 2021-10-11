package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import Models.AccountInfo;
import Models.Customer;
import menu.EmployeeMenu;
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
		
		Customer c1 = new Customer();
		
	Connection con = conUtil.getConnection();
	String sql = "SELECT * FROM bankuser WHERE bankuser.username='"+ userName + "'";
	try {
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery(sql);
		
		
		while(rs.next()) {
			c1.setId(rs.getInt(1));
			c1.setFirstName(rs.getString(2));
			c1.setLastName(rs.getString(3));
			c1.setUserName(rs.getString(4));
			c1.setPassword(rs.getString(5));
			c1.setUser_type(rs.getString(6));
			c1.setAccnt_aproved(rs.getBoolean(7));
		}
		
		//finish up the creation of the user that we're getting back on sign in
		
		
		
		return c1;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
	
		e.printStackTrace();
	}
	
		
		
		
		return null;
	}

	@Override
	public void createCustomer(Customer c) throws SQLException {
		
		
	Connection con = conUtil.getConnection();
	
	String sql = "Insert into bankuser(first_name, last_name, username, password, usertype, accnt_aproved) values (?, ?, ?, ?, 'customer', 'false')";
	
	
	PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, c.getFirstName());
		ps.setString(2, c.getLastName());
		ps.setString(3, c.getUserName());
		ps.setString(4, c.getPassword());
		
		ps.execute();
		
	}

	@Override
	public void updateCustomer(Customer c) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteustomer(Customer c) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	// THESE ARE FUNCTIONS FOR THE CUSTOMER
	// TO WITHDRAW TRANSFER AND DEPOSIT
	
	
	public AccountInfo deposit(Customer c, float amnt) throws SQLException{
		
		//BigDecimal value = new BigDecimal(Float.toString(123.4f));
		BigDecimal val = new BigDecimal(Float.toString(amnt));
		String sql = "call balanceupdate(?, ?)";
		Connection con = conUtil.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setInt(1, c.getId());
		ps.setBigDecimal(2, val);
		
		ps.execute();
		//Work on This!
		String sql1="SELECT * FROM accounts WHERE cus_id= '" + String.valueOf(c.getId())+"'";
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery(sql1);
		
		AccountInfo act = new AccountInfo();
		while(rs.next()) {
			act.setAccountNum(rs.getInt(1));
			act.setCusId(rs.getInt(2));;
			act.setAccountType(rs.getString(3));
			act.setBalance(rs.getFloat(4));
		}
		return act;
		
	}
	
	
	
	
	
	
	
	//EMPLOYEE FUNCTIONS for Employees to Use We're skipping using the Customer Service Class here
	
	
	
	
	
	
	
	
	public List<Customer> getUnapprovedCusts(Customer emp){
		
	
List<Customer> unApproved = new ArrayList<Customer>();
		
		try {
			Connection con = conUtil.getConnection();
			
			String sql = "SELECT * from bankuser WHERE accnt_aproved = false";
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			
			while(rs.next()) {
			unApproved.add( new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getBoolean(7)));
			}
			
			return unApproved;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("There are no unnapproved accounts");
		EmployeeMenu.EmpMenu1(emp);
		return null;
		
	}
	
	
	public void approvingAccounts(Customer c) {
		Connection con = conUtil.getConnection();
		
		
		
		try {
			String sql = "UPDATE bankuser SET accnt_aproved = 'true' WHERE id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			
			
			
			ps.setInt(1, c.getId());
			
			ps.execute();
			System.out.println("Customer account successfully updated");
			
			String sql1 = "INSERT INTO accounts(cus_id, accnt_type, balance) values(?, 'savings', 0.00)";
			
			
			
			PreparedStatement ps1 = con.prepareStatement(sql1);
			ps1.setInt(1, c.getId());
			
			ps1.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
