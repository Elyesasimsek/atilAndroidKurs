package com.elyesasimsek.newanimebook;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;

import com.elyesasimsek.newanimebook.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    static ArrayList<Bitmap> animeImageList;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        binding.toolbar.setTitle("Anime Book");
        setSupportActionBar(binding.toolbar);

        ArrayList<String> animeNameList = new ArrayList<>();
        animeImageList = new ArrayList<>();

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, animeNameList);
        binding.listView.setAdapter(adapter);

        String Url = "content://com.elyesasimsek.newanimebook.AnimeContentProvider";
        Uri animeUri = Uri.parse(Url);

        ContentResolver contentResolver = getContentResolver();
        @SuppressLint("Recycle") Cursor cursor= contentResolver.query(animeUri, null, null, null, "name");

        if (cursor != null){
            while (cursor.moveToNext()){
                animeNameList.add(cursor.getString(cursor.getColumnIndex(AnimeContentProvider.NAME)));
                byte[] bytes = cursor.getBlob(cursor.getColumnIndex(AnimeContentProvider.IMAGE));
                Bitmap image =BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                animeImageList.add(image);

                adapter.notifyDataSetChanged();
            }

        }
        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                intent.putExtra("info", "old");
                intent.putExtra("name", animeNameList.get(position));
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_art, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.addAnime){
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            intent.putExtra("info", "new");
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}