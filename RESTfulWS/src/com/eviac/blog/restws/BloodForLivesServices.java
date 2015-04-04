package com.eviac.blog.restws;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

/**
 * @author ketan
 */

@Path("bloodforlives")
public class BloodForLivesServices
{
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response DoLogin(Login input)
	{
		Profile pf = null;
		try
		{
			pf = DBHelper.MakeLogin(input);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(200)
				.entity(new Gson().toJson(pf, Profile.class)).build();
	}
	
	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response DoRegistration(Profile input)
	{
		/*
		 * Accept the profile object and enter into DB. Send the profile object back
		 */

		Profile pf = null;
		try
		{
			pf = DBHelper.RegisterUser(input);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(200)
				.entity(new Gson().toJson(pf, Profile.class)).build();
	}
	
	@POST
	@Path("/editprofile")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response DoEditProfile(Profile input)
	{
		/*
		 * Accept the profile object and update DB. Send the profile object back.
		 */
		
		Profile pf = null;
		try
		{
			pf = DBHelper.EditProfile(input);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(200)
				.entity(new Gson().toJson(pf, Profile.class)).build();
	}
	
	@POST
	@Path("/makesearch")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response DoSearch(Request input)
	{
		/*
		 * Accept the request object. Search the DB for match.
		 * Send the list of profiles into a single json object (MatchingProfiles).
		 */

		MatchingDonors donors = null;
		try
		{
			donors = DBHelper.MakeSearch(input);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Response.status(200)
				.entity(new Gson().toJson(donors, MatchingDonors.class)).build();
	}

}