package com.hornetincorporation.beatroute;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class StopServiceAlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service = new Intent(context, BeeterTrackingService.class);

        SharedPreferences sharedPref = context.getSharedPreferences(Constants.BEETER_TRACKING_SERVICE.PREF_FILE, Context.MODE_PRIVATE);
        if (sharedPref.contains(Constants.BEETER_TRACKING_SERVICE.IS_SERVICE_RUNNING)) {
            if (sharedPref.getString(Constants.BEETER_TRACKING_SERVICE.IS_SERVICE_RUNNING, "DEFAULT").equals("TRUE")) {
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString(Constants.BEETER_TRACKING_SERVICE.IS_SERVICE_RUNNING, "FALSE");
                editor.commit();
                context.stopService(service);
            }
        }
    }
}