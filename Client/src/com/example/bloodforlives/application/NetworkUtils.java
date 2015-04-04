package com.example.bloodforlives.application;

import java.util.HashMap;

public class NetworkUtils
{
	public static HashMap<String, String> getHeaders()
	{
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Accept", "application/json");
		headers.put("Content-Type", "application/json");
		return headers;
	}
}
