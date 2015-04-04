package com.example.bloodforlives;


public class Profile {

	public String phoneNumber;
	public String fname;
	public String lname;
	public String email;
	public String blood;
	public String dob;
	public String area;
	public int rating;

	public Profile() {
		
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
