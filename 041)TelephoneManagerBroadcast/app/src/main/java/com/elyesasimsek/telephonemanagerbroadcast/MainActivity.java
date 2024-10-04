package com.elyesasimsek.telephonemanagerbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    LocalBroadcastManager manager;
    private String caller;

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
        textView = findViewById(R.id.textView);
        manager = LocalBroadcastManager.getInstance(null);

        caller = "";
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String phoneNumber = intent.getStringExtra("phoneNumber");
            System.out.println("phoneNumber: " + phoneNumber);
            matchContact(phoneNumber);
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("my.result.receiver");
        manager.registerReceiver(receiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        manager.unregisterReceiver(receiver);
    }

    private void matchContact(String number){
        ArrayList<String> namesFromDB = new ArrayList<>();
        ArrayList<String> numbersFromDB = new ArrayList<>();

        HashMap<String, String> contactInfo = new HashMap<>();

        SQLiteDatabase db = this.openOrCreateDatabase("com.elyesasimsek.telephonemanagerbroadcast", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS phonebook (name VARCHAR, number VARCHAR)");
        db.execSQL("INSERT INTO phonebook (name, number) VALUES ('Elyesa', '555555')");
        db.execSQL("INSERT INTO phonebook (name, number) VALUES ('Fatma', '666666')");

        Cursor cursor = db.rawQuery("SELECT * FROM phoneBook", null);
        int nameIx = cursor.getColumnIndex("name");
        int numberIx = cursor.getColumnIndex("number");

        while (cursor.moveToNext()){
            String nameFromDB = cursor.getString(nameIx);
            String numberFromDB = cursor.getString(numberIx);

            namesFromDB.add(nameFromDB);
            numbersFromDB.add(numberFromDB);

            contactInfo.put(numberFromDB, nameFromDB);
        }
        cursor.close();
        for (String s: numbersFromDB){
            if (s.matches(number)){
                caller = contactInfo.get(number);
                textView.setText("caller: " + caller);
            }else {
                caller = "";
            }
        }

    }
}