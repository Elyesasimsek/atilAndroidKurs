package com.example.a007storingdata;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText editTextAge;
    private TextView textView;
    SharedPreferences sharedPreferences;

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

        editTextAge = findViewById(R.id.editTextAge);
        textView = findViewById(R.id.textView);

        sharedPreferences = this.getSharedPreferences("com.example.a007storingdata", Context.MODE_PRIVATE);
        int storedAge = sharedPreferences.getInt("storedAge", 0);
        textView.setText("Your Age: " + storedAge);
    }

    public void save(View view){
        if (!editTextAge.getText().toString().matches("")){
            int age = Integer.parseInt(editTextAge.getText().toString());
            textView.setText("Your Age: " + age);

            sharedPreferences.edit().putInt("storedAge",age).apply();
        }
    }

    public void delete(View view){
        int storedData = sharedPreferences.getInt("storedAge", 0);
        if (storedData != 0){
            sharedPreferences.edit().remove("storedAge").apply();
            textView.setText("Your Age: 0");
        }
    }
}