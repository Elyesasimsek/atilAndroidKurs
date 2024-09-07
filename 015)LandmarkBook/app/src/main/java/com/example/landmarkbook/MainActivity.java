package com.example.landmarkbook;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.landmarkbook.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Landmark> landmarkArrayList;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        landmarkArrayList = new ArrayList<>();

        binding.rv.setHasFixedSize(true);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));

       Landmark pisa = new Landmark("Pisa", "Italy", R.drawable.pisa);
       Landmark eifeel = new Landmark("Eifeel", "France", R.drawable.eyfel);
       Landmark colesseum = new Landmark("Colesseum", "Italy", R.drawable.colosseum);
       Landmark londonBridge = new Landmark("London Bridge", "UK", R.drawable.londonbridge);

       landmarkArrayList.add(pisa);
       landmarkArrayList.add(eifeel);
       landmarkArrayList.add(colesseum);
       landmarkArrayList.add(londonBridge);

       LandmarkAdapter landmarkAdapter = new LandmarkAdapter(landmarkArrayList);
       binding.rv.setAdapter(landmarkAdapter);
    }
}