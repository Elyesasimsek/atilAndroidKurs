package com.example.animebookfragment.View;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.room.Room;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.animebookfragment.Model.Anime;
import com.example.animebookfragment.R;
import com.example.animebookfragment.RoomDB.AnimeDAO;
import com.example.animebookfragment.RoomDB.AnimeDatabase;
import com.example.animebookfragment.databinding.FragmentAnimeBinding;
import com.google.android.material.snackbar.Snackbar;

import java.io.ByteArrayOutputStream;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AnimeFragment extends Fragment {

    private FragmentAnimeBinding binding;
    private ActivityResultLauncher<Intent> activityResultLauncher;
    private ActivityResultLauncher<String> permissionLauncher;
    private Bitmap selectedImage;
    private AnimeDAO animeDAO;
    private AnimeDatabase db;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    String into = "";
    private Anime anime;
    private SQLiteDatabase database;

    public AnimeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = Room.databaseBuilder(getActivity().getApplicationContext(), AnimeDatabase.class, "Anime").build();
        animeDAO = db.animeDAO();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_anime, container, false);
        registerLauncher();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        database = requireActivity().openOrCreateDatabase("Anime",Context.MODE_PRIVATE, null);

        if (getArguments() != null){
            into = AnimeFragmentArgs.fromBundle(getArguments()).getInfo();
        }else {
            into = "new";
        }

        binding.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save(v);
            }
        });

        binding.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage(v);
            }
        });

        if (into.equals("new")){
            into = "new";
            binding.editTextNameText.setText("");
            binding.editTextYearOfConstruction.setText("");
            binding.editTextIMDB.setText("");
            binding.imageView.setImageResource(R.drawable.selectimage);
            binding.buttonSave.setVisibility(View.VISIBLE);
        }else {
            int animeId = AnimeFragmentArgs.fromBundle(getArguments()).getAnimeId();
            binding.buttonSave.setVisibility(View.GONE);
            compositeDisposable.add(animeDAO.getArtById(animeId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(AnimeFragment.this::handleResponseNewOld));
        }
    }

    private void handleResponseNewOld(Anime anime){
        this.anime = anime;
        binding.editTextNameText.setText(anime.getName());
        binding.editTextYearOfConstruction.setText(anime.getYear());
        binding.editTextIMDB.setText(anime.getImdb());

        Bitmap bitmap = BitmapFactory.decodeByteArray(anime.getImage(), 0, anime.getImage().length);
        binding.imageView.setImageBitmap(bitmap);
    }

    public void save(View view){
        String name = binding.editTextNameText.getText().toString();
        String year = binding.editTextYearOfConstruction.getText().toString();
        String imdb = binding.editTextIMDB.getText().toString();

        Bitmap smallImage = makeSmallerImage(selectedImage, 200);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        smallImage.compress(Bitmap.CompressFormat.PNG, 50, outputStream);
        byte[] byteArray =  outputStream.toByteArray();

        anime = new Anime(name, year, imdb, byteArray);

        compositeDisposable.add(animeDAO.insert(anime).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(AnimeFragment.this::handleResponse));

    }

    private void handleResponse(){
        NavDirections navDirections = AnimeFragmentDirections.actionAnimeFragmentToMainFragment();
        Navigation.findNavController(requireView()).navigate(navDirections);
    }

    public Bitmap makeSmallerImage(Bitmap image, int maximumSize){
        int width = image.getWidth();
        int height = image.getHeight();
        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1){
            width = maximumSize;
            height = (int) (width / bitmapRatio);
        }else {
            height = maximumSize;
            width = (int) (height * bitmapRatio);
        }

        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    public void selectImage(View view){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), android.Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED){
                if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), android.Manifest.permission.READ_MEDIA_IMAGES)){
                    Snackbar.make(view, "Permission needed for gallery", Snackbar.LENGTH_INDEFINITE).setAction("Give Permission", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            permissionLauncher.launch(android.Manifest.permission.READ_MEDIA_IMAGES);
                        }
                    }).show();
                }else {
                    permissionLauncher.launch(android.Manifest.permission.READ_MEDIA_IMAGES);
                }
            }else {
                Intent intentToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                activityResultLauncher.launch(intentToGallery);
            }
        }else {
            if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) getActivity().getApplicationContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE)){
                    Snackbar.make(view, "Permission needed for gallery", Snackbar.LENGTH_INDEFINITE).setAction("Give Permission", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            permissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE);
                        }
                    }).show();
                }else {
                    permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
                }
            }else {
                Intent intentToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                activityResultLauncher.launch(intentToGallery);
            }
        }
    }

    private void registerLauncher(){
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onActivityResult(ActivityResult o) {
                if (o.getResultCode() == Activity.RESULT_OK){
                    Intent intentFromResult = o.getData();
                    if (intentFromResult != null){
                        Uri imageData = intentFromResult.getData();
                        binding.imageView.setImageURI(imageData);
                        try {
                            ImageDecoder.Source source = ImageDecoder.createSource(getActivity().getContentResolver(), imageData);
                            selectedImage = ImageDecoder.decodeBitmap(source);
                            binding.imageView.setImageBitmap(selectedImage);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        permissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean o) {
                if (o){
                    Intent intentToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    activityResultLauncher.launch(intentToGallery);
                }else {
                    Toast.makeText(getActivity().getApplicationContext(), "Permission needed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}