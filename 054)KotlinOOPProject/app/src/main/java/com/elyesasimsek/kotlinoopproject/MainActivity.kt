package com.elyesasimsek.kotlinoopproject

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

        //constructor
        var myUser = User("Elyesa",50)
        println(myUser.age.toString())
        println(myUser.name)
        println(myUser.information())

        //encapsulation
        var elyesa = Family("Elyesa", 25)
        elyesa.setAge(25)
        println(elyesa.getAge().toString())

        //inheritance
        var fatma = SuperFamily("Fatma", 25)
        println(fatma.getName())
        fatma.sing()

        //polymorohism
        //static polymorphism
        var mathematics = Mathematics()
        println(mathematics.sum())
        println(mathematics.sum(5, 6))
        println(mathematics.sum(5, 6, 7))

        //dinamic polymoophism
        val animal = Animal()
        animal.sing()

        val barley = Dog()
        barley.test()
        barley.sing()

        //abstarct & interface
        //var myPeople = People() olmuyor
        var myPiano = Piano()
        myPiano.brand = "Yamaha"
        myPiano.digital = false
        println(myPiano.roomName)
        myPiano.info()

        //lambda expressions
        fun printString(myString: String){
            println(myString)
        }

        printString("My Test String")

        val testString ={myString: String -> println(myString)}

        testString("My Lambda String")

        val multiplyLambda = {a: Int, b: Int -> a * b}
        println(multiplyLambda(5, 4))

        val mutiplyLambda2 : (Int, Int) -> Int = {a, b -> a*b}
        println(mutiplyLambda2(5, 5))

        //asynchrnous
        //callback function
        fun downloadFamily(url: String, completion:(Family) -> Unit){
            val meryem = Family("Meryem", 0)
            completion(meryem)
        }

        downloadFamily("metallica.com", { family ->
            println(family.getName())
        })











    }
}