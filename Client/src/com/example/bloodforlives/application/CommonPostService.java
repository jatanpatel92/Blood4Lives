
package com.example.bloodforlives.application;

import java.util.Map;

import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.bloodforlives.BloodForLives;
import com.example.bloodforlives.R;

/*
 * Common volley post service
 */
public class CommonPostService
{

	/**
	 * Executes a post service with specified parameters
	 * 
	 * @param baseFragment
	 *            the object to take control of progress dialog
	 * @param url
	 *            final url to hit
	 * @param params
	 *            body parameters to send as a part of post request
	 * @param listener
	 *            a response-success listener to process a successful request
	 */
	public static void executePostService(
			final NetworkEventListener networkEventListener, String url,
			JSONObject params, Response.Listener<JSONObject> listener)
	{

		networkEventListener.onStartNetworkEvent(R.string.loading);

		JsonObjectRequest postRequest = new JsonObjectRequest(url, params,
				listener, new ErrorListener()
				{
					@Override
					public void onErrorResponse(VolleyError arg0)
					{
						networkEventListener.onCompletedWithError(arg0);
					}
				})
		{
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError
			{
				return NetworkUtils.getHeaders();
			}
		};

		BloodForLives.getInstance().addToRequestQueue(postRequest);

	}
}
