package com.example.bloodforlives;

import com.google.gson.annotations.Expose;

public class Login {
	@Expose
	private String phoneNumber;
	
	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	public Login(String phone) {
		phoneNumber = phone;
	}

	public Login() {
	}
}
