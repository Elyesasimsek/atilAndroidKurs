package com.example.a003methodsandclasses;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    String username;

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

        System.out.println("on create called");
        testMethod();
        System.out.println(math(5,9));
        System.out.println(newMethod("Elyesa"));
        username = "Elyesa";
    }

    public void testMethod(){
        username = "Fatma";
        int x = 4 + 4;
        x = 9;
        System.out.println("value of x: " + x);
    }

    public int math(int a, int b){
        username = "Meryem";
        int x = 11;
        System.out.println("value of x in math: " + x);
        return a + b;
    }

    public String newMethod(String string){
        return string + " new method";
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("on resume called");
        testMethod();
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("on stop called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("on pause called");
    }
}