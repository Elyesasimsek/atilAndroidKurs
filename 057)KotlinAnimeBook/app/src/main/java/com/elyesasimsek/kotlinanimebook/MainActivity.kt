package com.elyesasimsek.kotlinanimebook

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.elyesasimsek.kotlinanimebook.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var animeList: ArrayList<Anime>
    private lateinit var adapter: AnimeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.toolbarMain.title = "Anime Book"
        setSupportActionBar(binding.toolbarMain)

        animeList = ArrayList<Anime>()
        adapter = AnimeAdapter(this, animeList)
        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.adapter = adapter

        try {
            val database = this.openOrCreateDatabase("Animes", MODE_PRIVATE, null)
            val cursor = database.rawQuery("SELECT * FROM animes", null)
            val animeNameIx = cursor.getColumnIndex("animeName")
            val idIx = cursor.getColumnIndex("id")

            while (cursor.moveToNext()){
                val name = cursor.getString(animeNameIx)
                val id = cursor.getInt(idIx)
                val anime = Anime(name, id)
                animeList.add(anime)
            }
            adapter.notifyDataSetChanged()
            cursor.close()
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.anime_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.addAnime){
            val intent = Intent(this@MainActivity, AnimeActivity::class.java)
            intent.putExtra("info", "new")
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}