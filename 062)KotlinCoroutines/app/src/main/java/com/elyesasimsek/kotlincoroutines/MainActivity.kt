package com.elyesasimsek.kotlincoroutines

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

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

       /* GlobalScope.launch {
            repeat(100000){
                launch {
                    println("android")
                }
            }
        }*/

        /*runblocking
        println("run blocking start")
        runBlocking {
            launch {
                delay(5000)
                println("run blocking")
            }
        }
        println("run blocking end")*/

        //globalScope
/*        println("global scope start")
        GlobalScope.launch {
            delay(5000)
            println("global scope")
        }
        println("global scope end")*/

        //corutie context
/*        println("coroutine scope start")
        CoroutineScope(Dispatchers.Default).launch {
            delay(5000)
            println("coroutine scope")
        }
        println("coroutine scope end")*/

       /* runBlocking {
            launch {
                delay(5000)
                println("run blocking")
            }
            coroutineScope {
                launch {
                    delay(3000)
                    println("coroutine scope")
                }
            }
        }*/

        //dispatchers
        //Dispatchers.Default -> CPU Intensive
        //Dispatchers.IO -> Input
        //Dispatchers.Main -> UI
        //Dispatchers.Unconfined ->Inherited dispatcher

   /*     runBlocking {
            launch(Dispatchers.Main) {
                println("Main Thread: ${Thread.currentThread().name}")
            }

            launch (Dispatchers.IO){
                println("IO Thread: ${Thread.currentThread().name}")
            }

            launch (Dispatchers.Default){
                println("Default Thread: ${Thread.currentThread().name}")
            }

            launch (Dispatchers.Unconfined){
                println("Unconfined Thread: ${Thread.currentThread().name}")
            }
        }*/

      /*  runBlocking {
            delay(2000)
            println("run blocking")
            myFunction()
        }*/

        //iki farklı fun u birleştirme
     /*   var userName = ""
        var userAge = 0

        runBlocking {
            val downloadedName = async {
                downloadName()
            }

            val downloadedAge = async {
                downloadAge()
            }

            userName = downloadedName.await()
            userAge = downloadedAge.await()

            println("${userName} ${userAge}")
        }*/

        /*runBlocking {
            val myJob = launch {
                delay(2000)
                println("job")

                val secondJob = launch {
                    println("job 2")
                }
            }

            myJob.invokeOnCompletion {
                println("my job end")
            }

            myJob.cancel()
        }*/

        runBlocking {
            launch (Dispatchers.Default) {
                println("Context: ${coroutineContext}")
                withContext(Dispatchers.IO){
                    println("Context: ${coroutineContext}")
                }
            }
        }
    }

    suspend fun downloadName(): String{
        delay(2000)
        val username = "Elyesa: "
        println("username download")
        return username
    }

    suspend fun downloadAge(): Int{
        delay(2000)
        val userAge = 25
        println("username download")
        return userAge
    }

    suspend fun myFunction(){
        coroutineScope {
            delay(5000)
            println("suspend function")
        }
    }
}