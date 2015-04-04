/**
 * A singleton application class that incorporates generalised logic
 * Use Retrofit for static apis and Volley for dynamic requests
 */
package com.example.bloodforlives;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.Volley;

public class BloodForLives extends Application
{

	// Device name constant. used in user signUp API

	public static final String ANDROID = "android";

	// Singleton instance
	private static BloodForLives sBloodForLivesApplication;

	/**
	 * Networking requirements for Volley
	 */

	/**
	 * Log or request TAG
	 */
	public static final String TAG = "VolleyPatterns";

	/**
	 * Global request queue for Volley
	 */
	private RequestQueue mRequestQueue;

	/**
	 * Creates and initializes all network related functionality. Initializes
	 * the Rest Adapter for network communications Note: This should only be
	 * called by HouserieApplication class & the manifest.
	 */
	public BloodForLives()
	{
	}

	@Override
	public void onCreate()
	{
		// TODO Auto-generated method stub
		super.onCreate();
		BloodForLives.sBloodForLivesApplication = this;
	}

	/**
	 * Return an instance of this class
	 * 
	 * @return the instance of current class
	 */
	public static synchronized final BloodForLives getInstance()
	{

		if (BloodForLives.sBloodForLivesApplication != null
				&& BloodForLives.sBloodForLivesApplication.mRequestQueue == null)
		{
			BloodForLives.sBloodForLivesApplication.mRequestQueue = Volley
					.newRequestQueue(BloodForLives.sBloodForLivesApplication
							.getApplicationContext());
		}

		return BloodForLives.sBloodForLivesApplication;
	}

	// --- Volley ----

	/**
	 * @return The Volley Request queue, the queue will be created if it is null
	 */
	public synchronized RequestQueue getRequestQueue()
	{
		if (this.mRequestQueue == null)
		{
			this.mRequestQueue = Volley
					.newRequestQueue(BloodForLives.sBloodForLivesApplication
							.getApplicationContext());
		}
		return this.mRequestQueue;
	}

	/**
	 * Adds the specified request to the global queue, if tag is specified then
	 * it is used else Default TAG is used.
	 * 
	 * @param req
	 * @param tag
	 */
	public <T> void addToRequestQueue(Request<T> request, String tag)
	{
		// set the default tag if tag is empty
		request.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
		VolleyLog.d("Adding request to queue: %s", request.getUrl());
		this.getRequestQueue().add(request);
	}

	/**
	 * Adds the specified request to the global queue using the Default TAG.
	 * 
	 * @param request
	 * @param tag
	 */
	public <T> void addToRequestQueue(Request<T> request)
	{
		// set the default tag if tag is empty
		request.setTag(TAG);
		this.getRequestQueue().add(request);
	}

	/**
	 * Cancels all pending requests by the specified TAG, it is important to
	 * specify a TAG so that the pending/ongoing requests can be cancelled.
	 * 
	 * @param tag
	 */
	public void cancelPendingRequests(Object tag)
	{
		if (this.mRequestQueue != null)
		{
			this.mRequestQueue.cancelAll(tag);
		}
	}

}