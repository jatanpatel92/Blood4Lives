package com.example.bloodforlives;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.bloodforlives.application.CommonPostService;
import com.example.bloodforlives.application.NetworkEventListener;
import com.google.gson.Gson;

public class MakeSearchActivity extends ActionBarActivity implements
		NetworkEventListener
{

	private Context mContext;
	private ProgressDialog pd;
	private String webSvc = Globals.BASE_URL + Globals.SEARCH_PATH;
	private ListView list;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_make_search);

		mContext = this;
		pd = new ProgressDialog(this);

		list = (ListView) findViewById(R.id.search_list);
		// Create request object
		Request request = new Request(Globals.myProfile.phoneNumber,
				Globals.myProfile.blood, Globals.myProfile.area);

		try
		{
			pd.setTitle("Loading...");
			pd.setMessage("Retreiving user information...");
			pd.show();
			this.search(request);
		} catch (JSONException e)
		{
			pd.dismiss();
			e.printStackTrace();
		}
	}

	private Response.Listener<JSONObject> mLoginResponseListener = new Response.Listener<JSONObject>()
	{

		@Override
		public void onResponse(JSONObject arg0)
		{
			Log.d(RegistrationActivity.class.getName(),
					"Success:" + arg0.toString());
			try
			{
				Gson gson = new Gson();

				ArrayList<String> stringArray = new ArrayList<String>();

				MatchingDonors md = gson.fromJson(arg0.toString(), MatchingDonors.class);
				ArrayList<Profile> matchingProfiles = new ArrayList<Profile>();
				matchingProfiles = md.getData();
				for (Profile matchProfile : matchingProfiles)
				{
					stringArray.add(String.format("%s %s\nContact : %s\nPlace : %s",
							matchProfile.fname.toUpperCase(), matchProfile.lname.toUpperCase(),
							matchProfile.phoneNumber, matchProfile.area.toUpperCase()));
				}

				ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, 
						R.layout.simplerow, R.id.rowTextView, stringArray);
				list.setAdapter(adapter);
				Toast.makeText(mContext, "Search successful", Toast.LENGTH_LONG).show();
				pd.dismiss();
			} catch (Exception e)
			{
				pd.dismiss();
				e.printStackTrace();
			}
		}
	};

	private final void search(Request request) throws JSONException
	{
		// TODO show progress dialog
		JSONObject jsonObject = new JSONObject(new Gson().toJson(request,
				Request.class));
		CommonPostService.executePostService(this, webSvc, jsonObject,
				this.mLoginResponseListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.make_search, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onCompletedWithError(VolleyError volleyError)
	{
		// TODO Auto-generated method stub
		pd.dismiss();
	}

	@Override
	public void onStartNetworkEvent(int resId)
	{
		// TODO Auto-generated method stub

	}
}
