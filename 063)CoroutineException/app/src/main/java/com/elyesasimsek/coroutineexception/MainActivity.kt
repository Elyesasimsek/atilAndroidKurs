package com.elyesasimsek.coroutineexception

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

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

        val handler = CoroutineExceptionHandler{ coroutineContext, throwable ->
            println("exception: ${throwable.localizedMessage}")
        }

        lifecycleScope.launch(handler) {
            throw Exception("error")
        }

        lifecycleScope.launch(handler) {
            launch {
                throw Exception("error")
            }

            launch {
                delay(500L)
                println("this is executed")
            }
        }

        lifecycleScope.launch(handler) {
            supervisorScope {
                launch {
                    throw Exception("error")
                }

                launch {
                    delay(500L)
                    println("this is executed")
                }
            }

        }
    }
}