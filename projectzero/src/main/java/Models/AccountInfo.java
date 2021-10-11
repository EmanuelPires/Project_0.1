package Models;

public class AccountInfo {
	
	private float balance;
	private int accountNum;
	private String accountType;
	private int cusId;
	
	
	@Override
	public String toString() {
		return "AccountInfo [balance=" + balance + ", accountNum=" + accountNum + ", accountType=" + accountType + "]";
	}
	
	
	public AccountInfo(float balance, int accountNum, String accountType) {
		super();
		this.balance = balance;
		this.accountNum = accountNum;
		this.accountType = accountType;
	}


	public AccountInfo() {
		// TODO Auto-generated constructor stub
	}


	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public int getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(int accountNum) {
		this.accountNum = accountNum;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}


	public int getCusId() {
		return cusId;
	}


	public void setCusId(int cusId) {
		this.cusId = cusId;
	}
	

}
