package com.elyesasimsek.fourscarecloneparse;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;

import com.elyesasimsek.fourscarecloneparse.databinding.ActivityCreatePlaceBinding;

import java.io.IOException;

public class CreatePlaceActivity extends AppCompatActivity {

    private ActivityCreatePlaceBinding binding;
    private Bitmap chosenImage;
    private ActivityResultLauncher<Intent> pickImageLauuncher;
    private static final int REQUEST_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_place);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        pickImageLauuncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult o) {
                handleImagePickResult(o);
            }
        });
    }

    public void next(View view){
        PlacesClass placesClass = PlacesClass.getInstance();

        String placeName = binding.editTextCreatePlaceActivityName.getText().toString();
        String placeType = binding.editTextCreatePlaceActivityType.getText().toString();
        String placeAtmosphere = binding.editTextCreatePlaceActivityatmosphere.getText().toString();

        placesClass.setName(placeName);
        placesClass.setType(placeType);
        placesClass.setAtmosphere(placeAtmosphere);
        placesClass.setImage(chosenImage);

        clickMap();
    }

    private void clickMap(){
        Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
        startActivity(intent);
    }

    public void selectPicture(View view){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_MEDIA_IMAGES}, REQUEST_PERMISSION);
            }else {
                pickImage();
            }
        }else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION);
            }else {
                pickImage();
            }
        }
    }

    private void pickImage(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickImageLauuncher.launch(intent);
    }

    private void handleImagePickResult(ActivityResult activityResult){
        if (activityResult.getResultCode() == RESULT_OK && activityResult.getData() != null){
            Uri uri = activityResult.getData().getData();
            try {
                ImageDecoder.Source source = ImageDecoder.createSource(this.getContentResolver(), uri);
                chosenImage = ImageDecoder.decodeBitmap(source);
                binding.imageViewCreatePlaceAtivity.setImageBitmap(chosenImage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION){
            if (grantResults.length > 0 && grantResults[0]  == PackageManager.PERMISSION_GRANTED){
                pickImage();
            }
        }
    }
}