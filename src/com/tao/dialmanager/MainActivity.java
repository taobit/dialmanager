package com.tao.dialmanager;

import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	private PackageManager manager;
	private boolean gsmSupported, cdmaSupported;
	private SharedPreferences pref;
	private Context context = this;
	private String number;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		manager = getPackageManager();
		gsmSupported = manager
				.hasSystemFeature(PackageManager.FEATURE_TELEPHONY_CDMA);
		cdmaSupported = manager
				.hasSystemFeature(PackageManager.FEATURE_TELEPHONY_GSM);
		pref = PreferenceManager.getDefaultSharedPreferences(context);
		number = pref.getString("number", null);
		Log.d("number", number + "");
		if (number != null) {
			TextView num = (TextView) findViewById(R.id.num);
			num.setText(number);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * Initiate the actions encoded in the specified URI.
	 */
	public void initiateSkypeUri(Context myContext, String mySkypeUri) {

		// Make sure the Skype for Android client is installed
		if (!isSkypeClientInstalled(myContext)) {
			goToMarket(myContext);
			return;
		}

		// Create the Intent from our Skype URI
		Uri skypeUri = Uri.parse(mySkypeUri);
		Intent myIntent = new Intent(Intent.ACTION_VIEW, skypeUri);

		// Restrict the Intent to being handled by the Skype for Android client
		// only
		myIntent.setComponent(new ComponentName("com.skype.raider",
				"com.skype.raider.Main"));
		myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		// Initiate the Intent. It should never fail since we've already
		// established the
		// presence of its handler (although there is an extremely minute window
		// where that
		// handler can go away...)
		myContext.startActivity(myIntent);

		return;
	}

	/**
	 * Determine whether the Skype for Android client is installed on this
	 * device.
	 **/
	public boolean isSkypeClientInstalled(Context myContext) {
		PackageManager myPackageMgr = myContext.getPackageManager();
		try {
			myPackageMgr.getPackageInfo("com.skype.raider",
					PackageManager.GET_ACTIVITIES);
		} catch (PackageManager.NameNotFoundException e) {
			return (false);
		}
		return (true);
	}

	/**
	 * Install the Skype client through the market: URI scheme.
	 **/
	public void goToMarket(Context myContext) {
		Uri marketUri = Uri.parse("market://details?id=com.skype.raider");
		Intent myIntent = new Intent(Intent.ACTION_VIEW, marketUri);
		myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		myContext.startActivity(myIntent);

		return;
	}

	public void call(View view) {
		Intent skype_intent = new Intent(
				"android.intent.action.CALL_PRIVILEGED");
		skype_intent.setClassName("com.skype.raider", "com.skype.raider.Main");
		skype_intent.setData(Uri.parse("tel:" + number));
		context.startActivity(skype_intent);
	}

}
