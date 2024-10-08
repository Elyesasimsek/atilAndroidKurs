package com.elyesasimsek.kotlinanimebook

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.elyesasimsek.kotlinanimebook.databinding.ActivityAnimeBinding
import com.google.android.material.snackbar.Snackbar
import java.io.ByteArrayOutputStream

class AnimeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnimeBinding
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var permissionLauncher: ActivityResultLauncher<String>
    var selectedBitmap: Bitmap? = null
    private lateinit var database: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_anime)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        database = this.openOrCreateDatabase("Animes", MODE_PRIVATE, null)

        registerLauncher()

        val intent = intent
        val info = intent.getStringExtra("info")
        if (info.equals("new")){
            binding.editTextNameText.setText("")
            binding.editTextYearOfConstruction.setText("")
            binding.editTextIMDB.setText("")
            binding.imageView.setImageResource(R.drawable.selectimage)
            binding.buttonSave.visibility = View.VISIBLE
        }else{
            binding.buttonSave.visibility = View.GONE
            val selectedId = intent.getIntExtra("id", 1)

            val cursor = database.rawQuery("SELECT * FROM animes WHERE id = ?", arrayOf(selectedId.toString()))
            val animeNameIx = cursor.getColumnIndex("animeName")
            val yearIx = cursor.getColumnIndex("year")
            val imdbIx = cursor.getColumnIndex("imdb")
            val imageIx = cursor.getColumnIndex("image")

            while (cursor.moveToNext()){
                binding.editTextNameText.setText(cursor.getString(animeNameIx))
                binding.editTextYearOfConstruction.setText(cursor.getString(yearIx))
                binding.editTextIMDB.setText(cursor.getString(imdbIx))
                val byteArray = cursor.getBlob(imageIx)
                val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
                binding.imageView.setImageBitmap(bitmap)
            }
            cursor.close()
        }
    }

    fun save (view: View){
        val animeName = binding.editTextNameText.text.toString()
        val year = binding.editTextYearOfConstruction.text.toString()
        val imdb = binding.editTextIMDB.text.toString()

        if (selectedBitmap != null){
            val smallBitmap = makeSmallerBitmap(selectedBitmap!!, 300)
            val outputStream = ByteArrayOutputStream()
            smallBitmap.compress(Bitmap.CompressFormat.PNG, 50, outputStream)
            val byteArray = outputStream.toByteArray()

            try {

                database.execSQL("CREATE TABLE IF NOT EXISTS animes (id INTEGER PRIMARY KEY, animeName VARCHAR, year VARCHAR, imdb VARCHAR, image BLOB)")
                val sqlString = "INSERT INTO animes (animeName, year, imdb, image) VALUES (?, ?, ?, ?)"
                val statement = database.compileStatement(sqlString)
                statement.bindString(1, animeName)
                statement.bindString(2, year)
                statement.bindString(3, imdb)
                statement.bindBlob(4, byteArray)
                statement.execute()
            }catch (e: Exception){
                e.printStackTrace()
            }
            val intent = Intent(this@AnimeActivity, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }

    fun selectImage (view: View){
       requestPermission(view)
    }

    private fun requestPermission(view: View) {
        val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_IMAGES
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }

        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                Snackbar.make(view, "Galeri için izin gerekiyor", Snackbar.LENGTH_INDEFINITE)
                    .setAction("İzin Ver") {
                        permissionLauncher.launch(permission)
                    }.show()
            } else {
                permissionLauncher.launch(permission)
            }
        } else {
            val intentToGallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            activityResultLauncher.launch(intentToGallery)
        }
    }


    private fun registerLauncher(){
        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK){
                val intentFromResult = result.data
                if (intentFromResult != null){
                    val imageData = intentFromResult.data
                    //binding.imageView.setImageURI(imageData)
                    if (imageData != null){
                        try {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                                val source = ImageDecoder.createSource(this@AnimeActivity.contentResolver, imageData)
                                selectedBitmap = ImageDecoder.decodeBitmap(source)
                                binding.imageView.setImageBitmap(selectedBitmap)
                            } else {
                                selectedBitmap = MediaStore.Images.Media.getBitmap(this@AnimeActivity.contentResolver, imageData)
                                binding.imageView.setImageBitmap(selectedBitmap)
                            }
                        }catch (e: Exception){
                            e.printStackTrace()
                        }
                    }
                }
            }
        }

        permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
            if (result){
                val intentToGallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentToGallery)
            }else{
                Toast.makeText(this@AnimeActivity, "Permission needed'", Toast.LENGTH_LONG).show()
            }

        }
    }

    private fun makeSmallerBitmap(image: Bitmap, maximumSize: Int): Bitmap {
        var width = image.width
        var height = image.height

        val bitmapRatio: Double = width.toDouble() / height.toDouble()

        if (bitmapRatio > 1) {
            width = maximumSize
            val sclaedHeight = width / bitmapRatio
            height = sclaedHeight.toInt()
        }else {
            height = maximumSize
            val scaledWidth = height / bitmapRatio
            width = scaledWidth.toInt()
        }
        return Bitmap.createScaledBitmap(image, width, height, true)
    }
}