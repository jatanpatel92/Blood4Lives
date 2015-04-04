package com.example.blood4lives;

public interface Config {

	// used to share GCM regId with application server - using php app server
	static final String APP_SERVER_URL = "http://192.168.0.7/gcm/gcmServer.php?shareRegId=1";

	// GCM server using java
	// static final String APP_SERVER_URL =
	// "http://192.168.1.17:8080/GCM-App-Server/GCMNotification?shareRegId=1";

	// Google Project Number
	static final String GOOGLE_PROJECT_ID = "566427989927";
	static final String MESSAGE_KEY = "m";

}
