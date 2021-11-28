package data.bean;

public class Account {
	private int id;
	private int account_id;
	private String username;
	private String password;
	private double money;
	
	public Account(){
		
	}
	
	public Account(int id,int account_id,String username,String password,double money){
		this.setId(id);
		this.setAccount_id(account_id);
		this.setUsername(username);
		this.setPassword(password);
		this.setMoney(money);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAccount_id() {
		return account_id;
	}

	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.id+"---"+this.account_id+"---"+this.username+"---"+this.password+"---"+this.money;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}
	
}
