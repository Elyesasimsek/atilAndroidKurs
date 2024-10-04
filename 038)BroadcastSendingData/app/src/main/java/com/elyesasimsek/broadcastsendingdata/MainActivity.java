package com.elyesasimsek.broadcastsendingdata;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void sendBroadcast(View view){
        Intent intent = new Intent(this, BroadcastInner.class);
        Bundle bundle = new Bundle();
        bundle.putString("name", "Elyesa");
        bundle.putInt("age", 25);
        intent.putExtras(bundle);
        sendBroadcast(intent);
    }

    public static class BroadcastInner extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            String name = bundle.getString("name");
            int age = bundle.getInt("age");

            Toast.makeText(context, "name: " + name + " Age: " + age, Toast.LENGTH_LONG).show();
        }
    }
}