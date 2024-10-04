package com.elyesasimsek.catchtheonepiecekotlin

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.elyesasimsek.catchtheonepiecekotlin.databinding.CardDesignBinding

class CatchAdapter :RecyclerView.Adapter<CatchAdapter.CardViewDesignObjectsHolder>{
    private lateinit var context: Context
    private lateinit var onePieceList: List<Character>

    constructor(context: Context, onePieceList: List<Character>) {
        this.context = context
        this.onePieceList = onePieceList
    }

    class CardViewDesignObjectsHolder(val binding: CardDesignBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewDesignObjectsHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding = DataBindingUtil.inflate<CardDesignBinding>(layoutInflater, R.layout.card_design, parent, false)
        return CardViewDesignObjectsHolder(binding)
    }

    override fun getItemCount(): Int {
        if (onePieceList == null){
            return 0;
        }else{
            return onePieceList.size
        }
    }

    override fun onBindViewHolder(holder: CardViewDesignObjectsHolder, position: Int) {
        val character = onePieceList[position]
        val binding = holder.binding
        binding.textViewCatch.text = character.characterName
        binding.cardViewLine.setOnClickListener {
            val intent = Intent(context, CatchActivity::class.java)
            intent.putExtra("character", character)
            context.startActivity(intent)
        }
    }
}