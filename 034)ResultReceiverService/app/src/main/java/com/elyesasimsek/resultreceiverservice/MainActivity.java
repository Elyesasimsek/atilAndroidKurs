package com.elyesasimsek.resultreceiverservice;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private TextView textView;

    Handler handler = new Handler();

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

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);
    }

    public void download(View view){
        ResultReceiver myResultReceiver = new MyResultReceiver(null);



        Intent intent = new Intent(this, DownloadClass.class);
        String userInput = editText.getText().toString() ;
        intent.putExtra("url", userInput);
        intent.putExtra("receiver", myResultReceiver);
        startService(intent);
    }

    public class  MyResultReceiver extends ResultReceiver{
        public MyResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);
            if (resultCode == 1 && resultData != null){
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        String result = resultData.getString("websiteResult");
                        textView.setText(result);
                    }
                });
            }
        }
    }
}