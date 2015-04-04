package com.example.bloodforlives;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ProfileActivity extends ActionBarActivity implements OnClickListener {

	private TextView pfName;
	private TextView pfBlood;
	private TextView pfDob;
	private TextView pfMail;
	private TextView pfArea;
	private TextView pfPhoneNumber;
	private Button btnEdit;
	private Profile loginProfile = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		loginProfile = Globals.myProfile;

		pfName = (TextView) this.findViewById(R.id.pf_name_textview);
		pfBlood = (TextView) this.findViewById(R.id.pf_blood_textview);
		pfDob = (TextView) this.findViewById(R.id.pf_dob_textview);
		pfMail = (TextView) this.findViewById(R.id.pf_mail_textview);
		pfArea = (TextView) this.findViewById(R.id.pf_area_textview);
		pfPhoneNumber = (TextView) this.findViewById(R.id.pf_contact_textview);
		btnEdit = (Button) super.findViewById(R.id.pf_edit_button);
		btnEdit.setOnClickListener(this);

		//Setup the text fields for profile page
		pfName.setText(loginProfile.fname + " " + loginProfile.lname);
		pfBlood.setText(loginProfile.blood);
		pfDob.setText(loginProfile.dob);
		pfMail.setText(loginProfile.email);
		pfArea.setText(loginProfile.area);
		pfPhoneNumber.setText(loginProfile.phoneNumber);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
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
		if(v == this.btnEdit)
		{
			Intent editProfileIntent = new Intent(this, EditProfileActivity.class);
			startActivity(editProfileIntent);
		}
		
	}
}
