package com.eviac.blog.restws;

import java.sql.Timestamp;
import java.util.Calendar;

public class Request {
	public String phoneNumber;
	public String bloodtype;
	public String area;

	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	public String getBloodtype()
	{
		return bloodtype;
	}

	public void setBloodtype(String bloodtype)
	{
		this.bloodtype = bloodtype;
	}

	public String getArea()
	{
		return area;
	}

	public void setArea(String area)
	{
		this.area = area;
	}

	public Request(String phone, String blood, String city) {
		phoneNumber = phone;
		bloodtype = blood;
		area = city;
	}
	
	public Request() {
		phoneNumber = "";
		bloodtype = "";
		area = "";
	}

}
