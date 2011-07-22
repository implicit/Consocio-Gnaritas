package com.coagmento.parsers;

public class LoginDataSet {
	private String name = "";
	private String password = "";
	private int userID = 0;
	private String loginName = "";
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	
	public int getUserID() { return userID; }
	public void setUserID(int userID) { this.userID = userID; }
	
	public String getLoginName() { return loginName; }
	public void setLoginName(String loginName) { this.loginName = loginName; }
	
	@Override
	public String toString() 
	{
		return "User Name: " + this.name + "  UserID: " + this.userID;
	}
}
