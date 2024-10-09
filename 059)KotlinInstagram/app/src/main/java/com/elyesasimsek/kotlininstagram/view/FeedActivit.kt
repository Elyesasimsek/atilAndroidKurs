package com.elyesasimsek.kotlininstagram.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.elyesasimsek.kotlininstagram.R
import com.elyesasimsek.kotlininstagram.adapter.PostAdapter
import com.elyesasimsek.kotlininstagram.databinding.ActivityFeedBinding
import com.elyesasimsek.kotlininstagram.model.Post
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore

class FeedActivit : AppCompatActivity() {

    private lateinit var binding: ActivityFeedBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private lateinit var postArrayList: ArrayList<Post>
    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_feed)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setSupportActionBar(binding.toolbarFeed)

        auth = Firebase.auth
        firestore = Firebase.firestore

        postArrayList = ArrayList<Post>()

        getData()

        binding.rv.layoutManager = LinearLayoutManager(this)
        postAdapter = PostAdapter(this, postArrayList)
        binding.rv.adapter = postAdapter
    }

    private fun getData(){
        firestore.collection("Posts").orderBy("date", Query.Direction.DESCENDING) .addSnapshotListener{ value, error ->
            if (error != null){
                Toast.makeText(this@FeedActivit, error.localizedMessage, Toast.LENGTH_LONG).show()
            }else{
                if (value != null){
                    if (!value.isEmpty){
                        val documents =  value.documents
                        postArrayList.clear()
                        for (document in documents){
                            val comment = document.get("comment") as String
                            val userEmail = document.get("userEmail") as String
                            val downloadUrl = document.get("downloadUrl") as String

                            val post = Post(userEmail, comment, downloadUrl)
                            postArrayList.add(post)
                        }
                        postAdapter.notifyDataSetChanged()
                    }
                }
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.option_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.addPost){
            val intent = Intent(this@FeedActivit, UploadActivity::class.java)
            startActivity(intent)
        }else if (item.itemId == R.id.signOut){
            auth.signOut()
            val intent = Intent(this@FeedActivit, MainActivity::class.java)
            finish()
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}