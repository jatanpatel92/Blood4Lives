package com.example.bloodforlives;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.bloodforlives.application.CommonPostService;
import com.example.bloodforlives.application.NetworkEventListener;
import com.google.gson.Gson;

public class MainActivity extends ActionBarActivity implements
		NetworkEventListener
{

	private Context mContext;
	private String webSvc = Globals.BASE_URL + Globals.LOGIN_PATH;
	private ProgressDialog pd;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mContext = this;
		pd  = new ProgressDialog(this);
		
		// Get the telephone number
		TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);

		// Create the Login Object
		Login login = new Login(tm.getLine1Number());

		// DB
		try
		{
			pd.setTitle("Loading...");
			pd.setMessage("Retreiving user information...");
			pd.show();
			this.login(login);
		} catch (JSONException e)
		{
			e.printStackTrace();
			pd.dismiss();
		}

		// new LoginServiceHandler(login, this).execute();
	}

	private final void login(Login login) throws JSONException
	{
		Log.d(super.getClass().getSimpleName(),
				new Gson().toJson(login, Login.class));
		JSONObject jsonObject = new JSONObject(new Gson().toJson(login,
				Login.class));
		CommonPostService.executePostService(this, webSvc, jsonObject,
				this.mLoginResponseListener);
	}

	private Response.Listener<JSONObject> mLoginResponseListener = new Response.Listener<JSONObject>()
	{

		@Override
		public void onResponse(JSONObject arg0)
		{
			Log.d(MainActivity.class.getName(), "Success:" + arg0.toString());

			Gson gson = new Gson();
			Profile pf = gson.fromJson(arg0.toString(), Profile.class);
			if (pf.phoneNumber.isEmpty())
			{
				pd.dismiss();
				// launch registration activity
				Intent registrationIntent = new Intent(mContext,
						RegistrationActivity.class);
				mContext.startActivity(registrationIntent);
			} else
			{
				pd.dismiss();
				// login successful launch home activity
				Intent homeIntent = new Intent(mContext, HomeActivity.class);
				mContext.startActivity(homeIntent);
				Globals.myProfile = pf;
			}
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
		pd.dismiss();
		// TODO Auto-generated method stub

	}

	@Override
	public void onStartNetworkEvent(int resId)
	{
		// TODO Auto-generated method stub

	}
}
