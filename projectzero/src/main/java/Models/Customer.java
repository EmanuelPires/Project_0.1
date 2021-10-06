package Models;

import java.io.Serializable;

public class Customer implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	
	

	public Customer(String firstName, String lastName, String userName, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
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
		return "Customer [firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName + ", password="
				+ password + "]";
	}
	

}
