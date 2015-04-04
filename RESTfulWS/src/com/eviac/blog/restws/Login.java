package com.eviac.blog.restws;

public class Login {
	
	private String phoneNumber;
	
	public Login(String phone) {
		phoneNumber = phone;
	}

	public Login() {
	}

public String getPhoneNumber()
{
	return phoneNumber;
}

public void setPhoneNumber(String phoneNumber)
{
	this.phoneNumber = phoneNumber;
}

}
