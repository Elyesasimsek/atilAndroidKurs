package com.example.javaoopdetails;

import android.os.Bundle;

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

        Kedi kedi1 = new Kedi("kedi1", "Sarı", "Kahverengi");
        Kedi ked2 = new Kedi();

        System.out.println(Kedi.turIsmi);
        kedi1.konusKedi();
        Kedi.konusKediStatic();

        Kopek kopek = new Kopek();
        Kopek kopek1 = new Kopek("Barley", "Sari", "Sari");
        System.out.println(kopek1);
    }
}