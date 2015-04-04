package com.example.bloodforlives;


public class Request {
	public String phoneNumber;
	public String bloodtype;
	public String area;

	public Request() {
	}

	public Request(String phone, String blood, String city) {
		phoneNumber = phone;
		bloodtype = blood;
		area = city;
	}

}
