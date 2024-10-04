package com.elyesasimsek.catchtheonepiecekotlin

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.elyesasimsek.catchtheonepiecekotlin.databinding.ActivityCatchBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Runnable
import kotlin.random.Random

class CatchActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCatchBinding
    private var score = 0
    private lateinit var imageViewArray: Array<ImageView>
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable
    private lateinit var character: Character

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_catch)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        character = intent.getSerializableExtra("character") as Character
        imageViewArray = arrayOf(
            binding.imageView1, binding.imageView2, binding.imageView3, binding.imageView4, binding.imageView5, binding.imageView6, binding.imageView7, binding.imageView8, binding.imageView9
        )

        for (imageView in imageViewArray){
            val url = "https://elyesasimsek.com/onepiece/image/${character.characterImage}"
            Picasso.get().load(url).into(imageView)
        }
        hideImages()

        score = 0

        object : CountDownTimer(10000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                binding.textViewTime.text = "Time: ${millisUntilFinished /1000}"
            }

            override fun onFinish() {
                binding.textViewTime.text = "Time Off"
                handler.removeCallbacks(runnable)

                for (image in imageViewArray){
                    image.visibility = View.INVISIBLE
                }

                AlertDialog.Builder(this@CatchActivity)
                    .setTitle("Restart")
                    .setMessage("Are you sure to restart game?")
                    .setPositiveButton("Yes", object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            val intent = Intent(this@CatchActivity, MainActivity::class.java)
                            finish()
                            startActivity(intent)
                        }
                    })
                    .setNegativeButton("No", object : DialogInterface.OnClickListener{
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            Toast.makeText(this@CatchActivity, "Game Over!", Toast.LENGTH_LONG).show()
                        }
                    })
                    .show()
            }
        }.start()
    }

    fun increaseScore(view: View){
        score++
        binding.textViewScore.text = "Score: ${score}"
    }

    fun hideImages(){
        handler = Handler(Looper.getMainLooper())
        runnable = object : Runnable{
            override fun run() {
                for (image in imageViewArray){
                    image.visibility = View.INVISIBLE
                }
                val random = Random
                val i = random.nextInt(9)
                imageViewArray[i].visibility = View.VISIBLE
                handler.postDelayed(this, 500)
            }
        }
        handler.post(runnable)
    }
}