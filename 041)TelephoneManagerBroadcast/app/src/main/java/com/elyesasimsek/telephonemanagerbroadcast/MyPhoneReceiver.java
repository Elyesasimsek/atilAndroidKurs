package com.elyesasimsek.telephonemanagerbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class MyPhoneReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.listen(new PhoneStateListener(){
            @Override
            public void onCallStateChanged(int state, String phoneNumber) {
                super.onCallStateChanged(state, phoneNumber);
                System.out.println("phoneNumber: " + phoneNumber);

                LocalBroadcastManager manager = LocalBroadcastManager.getInstance(context);
                Intent intentToMain = new Intent("my.result.receiver");
                intentToMain.putExtra("phoneNumber", phoneNumber);
                manager.sendBroadcast(intentToMain);
            }
        }, PhoneStateListener.LISTEN_CALL_STATE);
    }
}
