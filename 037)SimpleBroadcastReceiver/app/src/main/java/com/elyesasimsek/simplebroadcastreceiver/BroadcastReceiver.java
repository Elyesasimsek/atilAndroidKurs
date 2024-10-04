package com.elyesasimsek.simplebroadcastreceiver;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BroadcastReceiver extends android.content.BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Broadcast Class Broadcast", Toast.LENGTH_LONG).show();
    }
}
