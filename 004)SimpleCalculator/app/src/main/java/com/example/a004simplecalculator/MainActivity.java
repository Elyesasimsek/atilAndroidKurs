package com.example.a004simplecalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNumber1, editTextNumber2;
    private TextView textViewResultText;

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
        editTextNumber1 = findViewById(R.id.editTextNumber1);
        editTextNumber2 = findViewById(R.id.editTextNumber2);
        textViewResultText = findViewById(R.id.textViewResultText);
    }

    public void sum(View view){
        if (editTextNumber1.getText().toString().matches("") || editTextNumber2.getText().toString().matches("")){
            textViewResultText.setText("Enter Number");
        }else{
            int number1 =  Integer.parseInt(editTextNumber1.getText().toString());
            int number2 =  Integer.parseInt(editTextNumber2.getText().toString());
            int result = number1 + number2;
            textViewResultText.setText("Result: " + result);
        }
    }

    public void deduct(View view){
        if (editTextNumber1.getText().toString().matches("") || editTextNumber2.getText().toString().matches("")){
            textViewResultText.setText("Enter Number");
        }else{
            int number1 =  Integer.parseInt(editTextNumber1.getText().toString());
            int number2 =  Integer.parseInt(editTextNumber2.getText().toString());
            int result = number1 - number2;
            textViewResultText.setText("Result: " + result);
        }
    }

    public void multiply(View view){
        if (editTextNumber1.getText().toString().matches("") || editTextNumber2.getText().toString().matches("")){
            textViewResultText.setText("Enter Number");
        }else{
            int number1 =  Integer.parseInt(editTextNumber1.getText().toString());
            int number2 =  Integer.parseInt(editTextNumber2.getText().toString());
            int result = number1 * number2;
            textViewResultText.setText("Result: " + result);
        }
    }

    public void divide(View view){
        if (editTextNumber1.getText().toString().matches("") || editTextNumber2.getText().toString().matches("")){
            textViewResultText.setText("Enter Number");
        }else{
            int number1 =  Integer.parseInt(editTextNumber1.getText().toString());
            int number2 =  Integer.parseInt(editTextNumber2.getText().toString());
            int result = number1 / number2;
            textViewResultText.setText("Result: " + result);
        }
    }
}