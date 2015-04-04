package com.eviac.blog.restws;


public class Profile {

	public String phoneNumber;
	public String fname;
	public String lname;
	public String email;
	public String blood;
	public String dob;
	public String area;
	public int rating;

	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	public String getFname()
	{
		return fname;
	}

	public void setFname(String fname)
	{
		this.fname = fname;
	}

	public String getLname()
	{
		return lname;
	}

	public void setLname(String lname)
	{
		this.lname = lname;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getBlood()
	{
		return blood;
	}

	public void setBlood(String blood)
	{
		this.blood = blood;
	}

	public String getDob()
	{
		return dob;
	}

	public void setDob(String dob)
	{
		this.dob = dob;
	}

	public String getArea()
	{
		return area;
	}

	public void setArea(String area)
	{
		this.area = area;
	}

	public int getRating()
	{
		return rating;
	}

	public void setRating(int rating)
	{
		this.rating = rating;
	}


	public Profile() {
		fname = "";
		lname = "";
		email = "";
		blood = "";
		area = "";
		dob = "";
		phoneNumber = "";
		rating = 0;
		
	}

	public Profile(String fnameString, String lnameString, String emaiString, 
			String bloodString, String addressString, String dobString, String phoneString) {
		fname = fnameString;
		lname = lnameString;
		email = emaiString;
		blood = bloodString;
		area = addressString;
		dob = dobString;
		phoneNumber = phoneString;
	}
}
