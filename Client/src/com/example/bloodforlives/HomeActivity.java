package com.example.bloodforlives;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HomeActivity extends ActionBarActivity implements OnClickListener {

	private Button btnProfile;
	private Button btnSearch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		btnProfile = (Button) this.findViewById(R.id.profilebutton);
		btnSearch = (Button) this.findViewById(R.id.searchbutton);

		btnProfile.setOnClickListener(this);
		btnSearch.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
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
	public void onClick(View v)
	{
		if (v == this.btnSearch)
		{
			Intent searchIntent = new Intent(this, MakeSearchActivity.class);
			startActivity(searchIntent);
		} else if (v == this.btnProfile)
		{
			// Show profile page
			Intent profileIntent = new Intent(this, ProfileActivity.class);
			startActivity(profileIntent);
		}

	}
}
