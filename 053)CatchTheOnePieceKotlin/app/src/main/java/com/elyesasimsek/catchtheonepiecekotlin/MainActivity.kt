package com.elyesasimsek.catchtheonepiecekotlin

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.elyesasimsek.catchtheonepiecekotlin.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CatchAdapter
    private lateinit var onePieceCharacterList: List<Character>
    private lateinit var characterDIF: CharacterDAOInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        characterDIF = ApiUtils.getCharacterDAOInterface()
        binding.rv.setHasFixedSize(true)
        binding.rv.layoutManager = LinearLayoutManager(this)

        allCharacters()
    }

    fun allCharacters(){
        characterDIF.allCharacters().enqueue(object : Callback<OnePieceCharacterReply>{
            override fun onResponse(
                call: Call<OnePieceCharacterReply>,
                response: Response<OnePieceCharacterReply>
            ) {
                onePieceCharacterList = response.body()?.characters?: emptyList()
                adapter = CatchAdapter(this@MainActivity, onePieceCharacterList)
                binding.rv.adapter = adapter
            }

            override fun onFailure(call: Call<OnePieceCharacterReply>, t: Throwable) {

            }
        })
    }
}