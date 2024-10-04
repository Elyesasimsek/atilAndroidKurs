package com.elyesasimsek.calculatorkotlin

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.elyesasimsek.calculatorkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var number1: Double? = null
    var number2: Double? = null
    var result: Double? = null

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

    fun mySum(view: View){
        number1 = binding.editTextNumber1.text.toString().toDoubleOrNull()
        number2 = binding.editTextNumber2.text.toString().toDoubleOrNull()

        if (number1 != null && number2 != null){
            val result = number1!! + number2!!
            binding.textViewResult.text = "Result: ${result}"
        }else{
            binding.textViewResult.text = "Enter Number"
        }
    }

    fun mySub(view: View){
        number1 = binding.editTextNumber1.text.toString().toDoubleOrNull()
        number2 = binding.editTextNumber2.text.toString().toDoubleOrNull()

        if (number1 != null && number2 != null){
            result = number1!! - number2!!
            binding.textViewResult.text = "Result: ${result}"
        }else{
            binding.textViewResult.text = "Enter Number"
        }
    }

    fun myMul(view: View){
        number1 = binding.editTextNumber1.text.toString().toDoubleOrNull()
        number2 = binding.editTextNumber2.text.toString().toDoubleOrNull()

        if (number1 != null && number2 != null){
            result = number1!! * number2!!
            binding.textViewResult.text = "Result: ${result}"
        }else{
            binding.textViewResult.text = "Enter Number"
        }
    }

    fun myDiv(view: View){
        number1 = binding.editTextNumber1.text.toString().toDoubleOrNull()
        number2 = binding.editTextNumber2.text.toString().toDoubleOrNull()

        if (number1 != null && number2 != null){
            result = number1!! / number2!!
            binding.textViewResult.text = "Result: ${result}"
        }else{
            binding.textViewResult.text = "Enter Number"
        }
    }
}