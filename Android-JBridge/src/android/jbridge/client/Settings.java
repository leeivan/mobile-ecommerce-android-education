package android.jbridge.client;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class Settings extends PreferenceActivity
{
	
	private static final String OPT_WEBSERVER = "webserver";
	private static final String OPT_WEBSERVER_DEF = "http://10.0.2.2:8080";


	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings);
	}
	
	public static String getServerAddress(Context context){
		return PreferenceManager.getDefaultSharedPreferences(context).getString(OPT_WEBSERVER , OPT_WEBSERVER_DEF) + "/ecommerce_rest_server_web";
	}
	
}
