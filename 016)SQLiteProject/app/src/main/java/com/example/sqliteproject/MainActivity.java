package com.example.sqliteproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

        try {
            SQLiteDatabase database = this.openOrCreateDatabase("Family", MODE_PRIVATE, null);
            database.execSQL("CREATE TABLE IF NOT EXISTS family (id INTEGER PRIMARY KEY, name VARCHAR, age INT)");
            //database.execSQL("INSERT INTO family (name, age) VALUES ('Elyesa', 25)");
            //database.execSQL("INSERT INTO family (name, age) VALUES ('Fatma', 25)");
            //database.execSQL("INSERT INTO family (name, age) VALUES ('Meryem', 0)");

            //database.execSQL("UPDATE family SET age = 26 WHERE name = 'Fatma'");

            database.execSQL("DELETE FROM family WHERE id = 4");

            //Cursor cursor = database.rawQuery("SELECT * FROM family WHERE id  = 2", null);
            //Cursor cursor = database.rawQuery("SELECT * FROM family WHERE name LIKE 'E%'", null);
            Cursor cursor = database.rawQuery("SELECT * FROM family", null);

            int nameIx = cursor.getColumnIndex("name");
            int ageIx = cursor.getColumnIndex("age");
            int idIx = cursor.getColumnIndex("id");
            while (cursor.moveToNext()){
                System.out.println("Name: " + cursor.getString(nameIx));
                System.out.println("Age: " + cursor.getInt(ageIx));
                System.out.println("Id: " + cursor.getInt(idIx));
            }
            cursor.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}