package com.elyesasimsek.kotlinsqlite

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        try {
            val myDatabase = this.openOrCreateDatabase("Family", MODE_PRIVATE, null)
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS family (id INTEGER PRIMARY KEY, name VARCHAR, age Int)")
            //myDatabase.execSQL("INSERT INTO family (name, age) VALUES ('Fatma', 25)")
            myDatabase.execSQL("UPDATE family SET age = 26 WHERE name = 'Fatma'")

            myDatabase.execSQL("DELETE FROM family WHERE name = 'Fatma'")

            //val cursor = myDatabase.rawQuery("SELECT * From family WHERE name LIKE '%a'", null)
            val cursor = myDatabase.rawQuery("SELECT * From family", null)

            val nameIx = cursor.getColumnIndex("name")
            val ageIx = cursor.getColumnIndex("age")
            val idIx = cursor.getColumnIndex("id")

            while (cursor.moveToNext()){
                println("Name: ${cursor.getString(nameIx)}")
                println("Age: ${cursor.getInt(ageIx)}")
            }
            cursor.close()
        }catch (e: Exception){
            e.printStackTrace()
        }
    }
}