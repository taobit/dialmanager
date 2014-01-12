package com.tao.dialmanager;

import android.os.Bundle;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.view.Menu;

public class MainActivity extends Activity {

	private PackageManager manager;
	boolean gsmSupported, cdmaSupported;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		manager = getPackageManager();
		gsmSupported = manager
				.hasSystemFeature(PackageManager.FEATURE_TELEPHONY_CDMA);
		cdmaSupported = manager
				.hasSystemFeature(PackageManager.FEATURE_TELEPHONY_GSM);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
