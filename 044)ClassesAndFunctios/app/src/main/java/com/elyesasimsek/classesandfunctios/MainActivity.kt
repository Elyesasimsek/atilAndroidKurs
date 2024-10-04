package com.elyesasimsek.classesandfunctios

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var myTextView: TextView
    lateinit var myButton: Button
    lateinit var nameText: EditText
    lateinit var ageText: EditText
    lateinit var jabText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        myTextView = findViewById(R.id.textView)
        myButton = findViewById(R.id.button)
        nameText = findViewById(R.id.nameText)
        ageText = findViewById(R.id.ageText)
        jabText = findViewById(R.id.jobText)

      /*  myButton.setOnClickListener {
            myTextView.text = "Button Clicked 2"
        }*/

        println("Hello Kotlin")
        test()

        mySum(10, 15)
        val sumResult = mySum(40, 35)
        val result =  myMultiply(10, 20)

        println(sumResult)
        println(result)

        //class
        val homer = Simpson()
        homer.name = "Homer Simpson"
        homer.age = 50
        homer.job = "Nuclear"
        println(homer.name)

        val bart = Simpson()

        //nullability
        var myString :String? = null
        println(myString)

        var myAge : Int? = null

        //1) !! kesin var
        //println(myAge!! * 10)

        //2)safe call
        println(myAge?.minus(10))

        //3) if
        if (myAge != null){
            println(myAge.minus(10))
        }else{
            println("myAge is null")
        }

        //4) elvis
        println(myAge?.minus(10)?: -10)

        //5) let
        myAge?.let { it - 10 }
    }

    fun test(){
        println("Test Function")
    }

    fun mySum(a:Int, b:Int){
        myTextView.text = "Result: ${a+b}"
    }

    fun myMultiply(a:Int, b:Int) : Int{
        //println(a*b)
        return a*b
    }

    fun buttonClick(view: View){
        val name = nameText.text.toString()
        val age = ageText.text.toString().toIntOrNull()
        val job = jabText.text.toString()

        if (age != null){
            val simpson = Simpson(name, age, job)
            myTextView.text = "Name: ${simpson.name} Age: ${simpson.age} Job: ${simpson.job}"
        }else{
            myTextView.text = "Enter your age!"
        }

    }
}