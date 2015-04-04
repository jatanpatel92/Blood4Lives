/**
 * 
 */
package com.example.bloodforlives.application;

import com.android.volley.VolleyError;

public interface NetworkEventListener {
	public void onCompletedWithError(VolleyError volleyError);

	public void onStartNetworkEvent(int resId);
}
