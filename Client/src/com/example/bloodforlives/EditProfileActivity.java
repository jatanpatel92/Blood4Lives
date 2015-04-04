package com.example.bloodforlives;

import java.util.Calendar;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.bloodforlives.application.CommonPostService;
import com.example.bloodforlives.application.NetworkEventListener;
import com.google.gson.Gson;

import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
import android.text.InputFilter.LengthFilter;
import android.util.Log;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class EditProfileActivity extends ActionBarActivity implements OnClickListener, 
NetworkEventListener {

	private EditText fname;
	private EditText lname;
	private EditText area;
	private EditText email;
	private DatePicker dobDp;
	private Spinner bloodSpinner;
	private Button btnUpdate;

	private Context mContext;
	private String webSvc = Globals.BASE_URL + Globals.UPDATE_PATH;
	private ProgressDialog pd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_profile);
		
		mContext = this;
		pd = new ProgressDialog(this);
		
		fname = (EditText) super.findViewById(R.id.update_fname_edittext);
		lname = (EditText) super.findViewById(R.id.update_lname_edittext);
		area = (EditText) super.findViewById(R.id.update_address_edittext);
		email = (EditText) super.findViewById(R.id.update_mail_edittext);
		dobDp = (DatePicker) super.findViewById(R.id.update_dob_datePicker);
		dobDp.setCalendarViewShown(false);

		bloodSpinner = (Spinner) super.findViewById(R.id.update_blood_spinner);
		ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
				R.layout.support_simple_spinner_dropdown_item);
		bloodSpinner.setAdapter(adapter);
		adapter.addAll("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-");

		fname.setText(Globals.myProfile.fname);
		lname.setText(Globals.myProfile.lname);
		area.setText(Globals.myProfile.area);
		email.setText(Globals.myProfile.email);
		Calendar cal = Calendar.getInstance();
		String[] split = Globals.myProfile.dob.split("-");
		//dobDp.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), null);
		dobDp.init(Integer.parseInt(split[0]), Integer.parseInt(split[1]) - 1, 
				Integer.parseInt(split[2]), null);
		bloodSpinner.setSelection(adapter.getPosition(Globals.myProfile.blood));
		
		btnUpdate = (Button) super.findViewById(R.id.update_button);
		btnUpdate.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_profile, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		if(v == this.btnUpdate)
		{
			//Convert date to string
			String dateString = new String(String.valueOf(dobDp.getYear())
					+ "-" + String.valueOf(dobDp.getMonth() + 1) + "-"
					+ String.valueOf(dobDp.getDayOfMonth()));

			//Get telephone number
			TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
			
			//Create profile object
			Profile profile = new Profile(fname.getText().toString(), lname
					.getText().toString(), email.getText().toString(),
					bloodSpinner.getSelectedItem().toString(), area.getText()
							.toString(), dateString, tm.getLine1Number());

			try {
				pd.setTitle("Loading...");
				pd.setMessage("Retreiving user information...");
				pd.show();
				this.update(profile);
			} catch (JSONException e) {
				e.printStackTrace();
				pd.dismiss();
			}
		}		
	}
	
	private Response.Listener<JSONObject> mLoginResponseListener = new Response.Listener<JSONObject>() {

		@Override
		public void onResponse(JSONObject arg0) {
			Log.d(EditProfileActivity.class.getName(), "Success:" + arg0.toString());
			Gson gson = new Gson();
			Profile pf = gson.fromJson(arg0.toString(), Profile.class);
			if(pf == null)
			{
				// launch registration activity
				Log.e(EditProfileActivity.class.getName(), "Null profile : " + arg0.toString());
				Intent profileIntent = new Intent(mContext, ProfileActivity.class);
				mContext.startActivity(profileIntent);
				Toast.makeText(mContext, "User not registerd. Proceed to registration", Toast.LENGTH_LONG).show();
				pd.dismiss();
			}
			else
			{
				// login successful launch home activity
				Intent homeIntent = new Intent(mContext, HomeActivity.class);
				mContext.startActivity(homeIntent);
				Globals.myProfile = pf;
				Toast.makeText(mContext, "Profile edited successfully.", Toast.LENGTH_LONG).show();
				pd.dismiss();
			}
		}
	};

	private final void update(Profile profile) throws JSONException {
		// TODO show progress dialog
		JSONObject jsonObject = new JSONObject(new Gson().toJson(profile,
				Profile.class));
		CommonPostService.executePostService(this, webSvc, jsonObject,
				this.mLoginResponseListener);
	}

	@Override
	public void onCompletedWithError(VolleyError volleyError) {
		// TODO Auto-generated method stub
		pd.dismiss();
	}

	@Override
	public void onStartNetworkEvent(int resId) {
		// TODO Auto-generated method stub
		
	}

}
