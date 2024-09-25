package com.elyesasimsek.mynewdatabase;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;

import com.elyesasimsek.mynewdatabase.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private ActivityMainBinding binding;

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

        try {
            db = this.openOrCreateDatabase("Family", MODE_PRIVATE, null);
            //db.execSQL("CREATE TABLE IF NOT EXISTS family (name VARCHAR, age INT(2))");
            //db.execSQL("INSERT INTO family (name, age) VALUES ('Elyesa', 25)");
            db.execSQL("DROP TABLE IF EXISTS family");

            db.execSQL("CREATE TABLE family (uniqueId INTEGER PRIMARY KEY AUTOINCREMENT, name VARHAR, age Int(2))");

            db.execSQL("INSERT INTO family (name, age) VALUES ('Elyesa', 25)");
            db.execSQL("INSERT INTO family (name, age) VALUES ('Fatma', 25)");

            Cursor cursor = db.rawQuery("SELECT * FROM family", null);

            int idIx = cursor.getColumnIndex("uniqueId");
            int nameIx = cursor.getColumnIndex("name");
            int ageIx = cursor.getColumnIndex("age");

            while (cursor.moveToNext()){
                System.out.println("Name: " + cursor.getString(nameIx));
                System.out.println("Age: " + cursor.getString(ageIx));
                System.out.println("Id: " + cursor.getString(idIx));
            }
            cursor.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}