package Models;

import java.io.Serializable;

public class Customer implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String firstName;
	private String lastName;
	private String user_type;
	private String userName;
	private String password;
	private boolean accnt_aproved;
	
	

	public Customer(int id, String firstName, String lastName, String user_type, String userName, String password) {
		super();
		this.id=id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.user_type = user_type;
		this.userName = userName;
		this.password = password;
		this.accnt_aproved=false;
		
	}
	
	
	public Customer(int id, String firstName, String lastName, String user_type, String userName, String password, Boolean accnt_aproved) {
		super();
		this.id=id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.user_type = user_type;
		this.userName = userName;
		this.password = password;
		this.accnt_aproved= accnt_aproved;
		
	}
	
	
	
	public boolean isAccnt_aproved() {
		return accnt_aproved;
	}

	public void setAccnt_aproved(boolean accnt_aproved) {
		this.accnt_aproved = accnt_aproved;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	public Customer(String firstName, String lastName, String userName, String password) {
		super();
		this.firstName=firstName;
		this.lastName=lastName;
		this.userName=userName;
		this.password= password;
	}
	
	public Customer() {
		super();
	}
	
	

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "Customer [ ID= " + id + " firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName + ", password="
				+ password + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

}
