package com.example.bloodforlives;

import java.util.ArrayList;

public class MatchingDonors
{
	public ArrayList<Profile> data;

	public ArrayList<Profile> getData()
	{
		return data;
	}

	public void setData(ArrayList<Profile> data)
	{
		this.data = data;
	}

	public MatchingDonors()
	{
		data = new ArrayList<Profile>();
	}
}
