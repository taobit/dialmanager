package com.tao.dialmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class DialBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
			String number = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
			SharedPreferences pref = PreferenceManager
					.getDefaultSharedPreferences(context);
			SharedPreferences.Editor editor = pref.edit();
			editor.putString("number", number);
			editor.commit();
		}

	}

}
