package com.elyesasimsek.kotlinlearning

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

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
        //Variables (var) Değiştirilebilir   Constants (val) Değiştirilemez
        //Integer
        println("--------------Integer----------")
        var x = 5
        var y = 4
        println(x * y)

        var age = 35
        val result = age / 7 * 5
        println(result)

        //Defining
        val myInteger : Int
        //Initialize
        myInteger = 10

        val a : Int = 23
        println(a / 2)

        var myLong : Long = 100

        //Double Float

        println("-----------Double-------------")
        val pi = 3.14
        println(pi * 2)

        val myFloat = 3.14f
        println(myFloat * 2)

        var myAge : Double
        myAge = 23.0
        println(myAge / 2)

        //String
        println("-----------String-------------")
        val myString = "elyesa ŞİMŞEK"
        val name = "Fatma"
        val surname = "ŞİMŞEK"

        val fullname = name + " " + surname
        println(fullname)

        val larsName : String = "Meryem"
        println(myString.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() })

        //Boolean
        println("-----------Boolean-------------")
        var myBoolean : Boolean = true
        myBoolean = false

        var isAlive = true

        println(3 < 5)
        println(6 < 5)

        //Conversion
        println("-----------Conversion-------------")

        var myNumber = 5
        var myLongNumber = myNumber.toLong()
        println(myLongNumber)

        var input = "10"
        var inputInteger =  input.toInt()
        println(inputInteger * 2)

        //Collections
        println("-----------Collections-------------")
        //Arrays
        println("-----------Array-------------")
        val myArray = arrayOf("Elyesa", "Fatma", "Meryem")
        println(myArray[0])
        myArray[0] = "Bekir"
        println(myArray[0])

        println(myArray)

        myArray.set(1, "Rabia")
        println(myArray[1])

        val numberArray = arrayOf(1, 2, 3, 4, 5)
        println(numberArray[3])

        val myNewArray = doubleArrayOf(1.0, 2.0, 3.0)

        val mixedArray = arrayOf("Elyesa", 5)
        println(mixedArray[0])
        println(mixedArray[1])

        //list - Arraylist
        println("-----------List-------------")

        val myFamily = arrayListOf<String>( "Elyesa", "Fatma")
        println(myFamily[0])
        myFamily.add("Meryem")

        val myArrayList = ArrayList<Int>()
        myArrayList.add(1)
        myArrayList.add(200)

        val myMixedArrayList = ArrayList<Any>()
        myMixedArrayList.add("Elyesa")
        myMixedArrayList.add(12.25)
        myMixedArrayList.add(true)

        println(myMixedArrayList[0])
        println(myMixedArrayList[1])
        println(myMixedArrayList[2])

        //set
        println("-----------Set-------------")
        val myExampleArray = arrayOf(1, 1, 2, 3, 4)
        println("First element: " + myExampleArray[0])
        println("Second element: ${myExampleArray[2]}")

        val mySet = setOf<Int>(1, 1, 2, 3, 4)
        println(mySet.size)

        //For Each
        mySet.forEach{println(it)}

        val myStringSet = HashSet<String>()
        myStringSet.add("Elyesa")
        myStringSet.add("Elyesa")
        println(myStringSet.size)

        //map HashMap
        println("-----------Map-------------")
        //Key - Value

        val fruitArray = arrayOf("Apple", "Banana")
        val caloriesArray = arrayOf( 100, 150)

        println("${fruitArray[0]} : ${caloriesArray[0]}")

        val fruitCalorieMap = hashMapOf<String, Int>()
        fruitCalorieMap.put("Apple", 100)
        fruitCalorieMap.put("Banana", 150)
        println(fruitCalorieMap["Apple"])

        val myHashmap = HashMap<String, String>()

        val myNewMap = hashMapOf<String, Int>("A" to 1, "B" to 2, "C" to 3)
        println(myNewMap["C"])

        //Operatorler
        println("-----------Operatorler-------------")

        var m = 5
        println(m)
        m = m + 1
        println(m)
        m++
        println(m)
        m--
        println(m)

        var n = 7
        println(n > m)

        // && ve
        // || veya

        println(n > m && 2 > 1)
        println(n > m && 2 < 1)
        println(n > m || 2 < 1)

        //if
        println("-----------if-------------")

        val myNumberAge = 32
        if (myNumberAge < 30) {
            println("< 30")
        }else if (myNumberAge >= 30 && myNumberAge < 40){
            println("30 - 40")
        }else if (myNumberAge >= 40 && myNumberAge < 50){
            println("40 - 50")
        }else{
            println(">= 50")
        }

        //Switch  - When
        println("-----------Switch-------------")

        val day = 3
        var dayString = ""

        when(day){
            1 -> dayString = "Monday"
            2 -> dayString = "Tuesday"
            3 -> dayString = "Wendesday"
            else -> dayString = ""
        }
        println(dayString)

        //Loops
        //For Loop
        println("-----------For-------------")

        val myArrayOfNumbers = arrayOf(1, 2, 3, 4, 5, 6, 7, 8)
        val q = myArrayOfNumbers[0] / 3 * 5
        println(q)

        for (number in myArrayOfNumbers){
            val z = number / 3 * 5
            println(z)
        }

        for (i in myArrayOfNumbers.indices){
            val qz = myArrayOfNumbers[i] / 3 * 5
            println(qz)
        }

        for (b in 0..9){
            println(b)
        }

        val myStringArrayList = ArrayList<String>()
        myStringArrayList.add("Elyesa")
        myStringArrayList.add("Fatma")
        myStringArrayList.add("Meryem")

        for (str in myStringArrayList){
            println(str)
        }

        myStringArrayList.forEach { println(it) }

        //While Loop
        println("-----------While-------------")

        var j = 0
        while (j < 10){
            println(j)
            j++
        }
    }
}