package com.elyesasimsek.runnablekotlin

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.elyesasimsek.runnablekotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var number = 0
    var runnable : Runnable = kotlinx.coroutines.Runnable {  }
    var handler: Handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun start(view: View){
        runnable = object : Runnable{
            override fun run() {
                number++
                binding.textView.text = "Time: ${number}"
                handler.postDelayed(runnable, 1000)
            }
        }
        handler.post(runnable)
        binding.button.isEnabled = false
    }

    fun stop(view: View){
        binding.button.isEnabled= true
        number = 0
        binding.textView.text = "Time: ${number}"
        handler.removeCallbacks(runnable)
    }
}