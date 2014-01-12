package com.tao.dialmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class DialBroadcastReceiver extends BroadcastReceiver {
	
	private SharePreference pref;

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
			String number = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
			Log.v("Receiver", number);
		}

	}

}
