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
		
		String sql2 = "INSERT into transactions (cus_id, trantype, amount) values (?, 'deposit', ?)";
		PreparedStatement ps2 = con.prepareStatement(sql2);
		ps2.setInt(1, c.getId());
		ps2.setBigDecimal(2, val);
		ps2.execute();
		
		return act;
		
	}
	
	
	public AccountInfo withdraw(Customer c, float amnt) throws SQLException {
		BigDecimal val = new BigDecimal(Float.toString(amnt));
		BigDecimal neg = val.negate();
		
		System.out.println(neg);
		String sql = "call balanceupdate(?, ?)";
		Connection con = conUtil.getConnection();
	
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, c.getId());
			ps.setBigDecimal(2, neg);
			ps.execute();
			

			
	
		
		String sql1 = "SELECT * FROM accounts WHERE cus_id='" + String.valueOf(c.getId())+"'";
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery(sql1);
		
		AccountInfo act = new AccountInfo();
		while(rs.next()) {
			act.setAccountNum(rs.getInt(1));
			act.setCusId(rs.getInt(2));;
			act.setAccountType(rs.getString(3));
			act.setBalance(rs.getFloat(4));
		}
		
		
		String sql2 = "INSERT into transactions (cus_id, trantype, amount) values (?, 'withdrawal', ?)";
		PreparedStatement ps2 = con.prepareStatement(sql2);
		ps2.setInt(1, c.getId());
		ps2.setBigDecimal(2, val);
		ps2.execute();
		return act;
		
	}
	
	
	public void transfer (Customer c, int cusId, float amnt)throws SQLException {
		
		AccountInfo curAccount = withdraw(c, amnt);
		Connection con = conUtil.getConnection();
		String sql="SELECT * FROM bankuser WHERE id='" + cusId + "'";
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery(sql);
		Customer c1 = new Customer();
		while(rs.next()) {
			c1.setId(rs.getInt(1));
			c1.setFirstName(rs.getString(2));
			c1.setLastName(rs.getString(3));
			c1.setUserName(rs.getString(4));
			c1.setPassword(rs.getString(5));
			c1.setUser_type(rs.getString(6));
			c1.setAccnt_aproved(rs.getBoolean(7));
		}
		AccountInfo tranAccount= deposit(c1, amnt);
		
		BigDecimal tran = new BigDecimal(Float.toString(amnt));
		
//		String sql1 = "INSERT into transactions (cus_id, trantype, amount) values (?, 'transfer', ?)";
//		PreparedStatement ps1 = con.prepareStatement(sql1);
//		ps1.setInt(1, c1.getId());
//		ps1.setBigDecimal(2, tran);
//		ps1.execute();
//		
//		
//		String sql2 = "INSERT into transactions (cus_id, trantype, amount) values (?, 'transfer', ?)";
//		PreparedStatement ps2 = con.prepareStatement(sql2);
//		ps2.setInt(1, c.getId());
//		ps2.setBigDecimal(2, tran.negate());
//		ps2.execute();
		
		
		
		
		
		
		
		
		System.out.println("Your transfer is complete");
		System.out.println("You transfereced $" + amnt + " to " + c1.getFirstName() + " " + c1.getLastName() );
		
		
		
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
	
	
	
	//ADMINISTRATOR FUNCTIONS
	
	
	
	public AccountInfo adminDeposit(int cusId, float amnt) throws SQLException{
		
		//BigDecimal value = new BigDecimal(Float.toString(123.4f));
		BigDecimal val = new BigDecimal(Float.toString(amnt));
		String sql = "call balanceupdate(?, ?)";
		Connection con = conUtil.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setInt(1, cusId);
		ps.setBigDecimal(2, val);
		
		ps.execute();
		//Work on This!
		String sql1="SELECT * FROM accounts WHERE cus_id= '" + String.valueOf(cusId)+"'";
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery(sql1);
		
		AccountInfo act = new AccountInfo();
		while(rs.next()) {
			act.setAccountNum(rs.getInt(1));
			act.setCusId(rs.getInt(2));;
			act.setAccountType(rs.getString(3));
			act.setBalance(rs.getFloat(4));
		}
		
		String sql2 = "INSERT into transactions (cus_id, trantype, amount) values (?, 'deposit', ?)";
		PreparedStatement ps2 = con.prepareStatement(sql2);
		ps2.setInt(1, cusId);
		ps2.setBigDecimal(2, val);
		ps2.execute();
		System.out.println("Deposit complete");
		return act;
		
	}
	
	
	public AccountInfo adminWithdraw(int cusId, float amnt) throws SQLException {
		BigDecimal val = new BigDecimal(Float.toString(amnt));
		BigDecimal neg = val.negate();
		
		System.out.println(neg);
		String sql = "call balanceupdate(?, ?)";
		Connection con = conUtil.getConnection();
	
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, cusId);
			ps.setBigDecimal(2, neg);
			ps.execute();
			

			
	
		
		String sql1 = "SELECT * FROM accounts WHERE cus_id='" + String.valueOf(cusId)+"'";
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery(sql1);
		
		AccountInfo act = new AccountInfo();
		while(rs.next()) {
			act.setAccountNum(rs.getInt(1));
			act.setCusId(rs.getInt(2));;
			act.setAccountType(rs.getString(3));
			act.setBalance(rs.getFloat(4));
		}
		
		
		String sql2 = "INSERT into transactions (cus_id, trantype, amount) values (?, 'withdrawal', ?)";
		PreparedStatement ps2 = con.prepareStatement(sql2);
		ps2.setInt(1, cusId);
		ps2.setBigDecimal(2, val);
		ps2.execute();
		
		System.out.println("Withdrawal complete!");
		return act;
		
	}
	
	
	public void adminCancelAccount(int cusId) {
		Connection con = conUtil.getConnection();
		
		
		
		try {
			String sql = "UPDATE bankuser SET accnt_aproved = 'false' WHERE id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			
			
			
			ps.setInt(1, cusId);
			
			ps.execute();
			System.out.println("Customer account canceled");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
public void adminTransfer (int cusId1, int cusId2, float amnt)throws SQLException {
		
	//BigDecimal value = new BigDecimal(Float.toString(123.4f));
			BigDecimal val = new BigDecimal(Float.toString(amnt));
			String sqlN = "call balanceupdate(?, ?)";
			Connection con = conUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(sqlN);
			
			ps.setInt(1, cusId1);
			ps.setBigDecimal(2, val);
			
			ps.execute();
			//Work on This!
			String sql="SELECT * FROM accounts WHERE cus_id= '" + String.valueOf(cusId1)+"'";
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			AccountInfo act = new AccountInfo();
			while(rs.next()) {
				act.setAccountNum(rs.getInt(1));
				act.setCusId(rs.getInt(2));;
				act.setAccountType(rs.getString(3));
				act.setBalance(rs.getFloat(4));
			}
			
			String sql1 = "INSERT into transactions (cus_id, trantype, amount) values (?, 'deposit', ?)";
			PreparedStatement ps2 = con.prepareStatement(sql1);
			ps2.setInt(1, cusId1);
			ps2.setBigDecimal(2, val);
			ps2.execute();
			
			
			
			BigDecimal neg = val.negate();
			
			System.out.println(neg);
			String sql2 = "call balanceupdate(?, ?)";
			Connection con2 = conUtil.getConnection();
		
				PreparedStatement ps3 = con2.prepareStatement(sql2);
				ps3.setInt(1, cusId1);
				ps3.setBigDecimal(2, neg);
				ps3.execute();
				

				
		
			
			String sql3 = "SELECT * FROM accounts WHERE cus_id='" + String.valueOf(cusId2)+"'";
			Statement s1 = con.createStatement();
			ResultSet rs1 = s1.executeQuery(sql3);
			
			AccountInfo act2 = new AccountInfo();
			while(rs1.next()) {
				act2.setAccountNum(rs1.getInt(1));
				act2.setCusId(rs1.getInt(2));;
				act2.setAccountType(rs1.getString(3));
				act2.setBalance(rs1.getFloat(4));
			}
			
			
			String sql4 = "INSERT into transactions (cus_id, trantype, amount) values (?, 'withdrawal', ?)";
			PreparedStatement ps4 = con.prepareStatement(sql4);
			ps4.setInt(1, cusId2);
			ps4.setBigDecimal(2, val);
			ps4.execute();
			
			System.out.println("Withdrawal complete!");
		
		
	}






	
	
}
