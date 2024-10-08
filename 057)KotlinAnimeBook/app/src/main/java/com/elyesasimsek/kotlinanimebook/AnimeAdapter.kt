package com.elyesasimsek.kotlinanimebook

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.elyesasimsek.kotlinanimebook.databinding.RecyclerRowBinding

class AnimeAdapter (
    private var context: Context,
    private var animeList: List<Anime>
): RecyclerView.Adapter<AnimeAdapter.AnimeHolder>(){
    class AnimeHolder(val binding: RecyclerRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding = DataBindingUtil.inflate<RecyclerRowBinding>(layoutInflater, R.layout.recycler_row, parent, false)
        return AnimeHolder(binding)
    }

    override fun getItemCount(): Int {
        if (animeList == null){
            return 0;
        }else{
            return animeList.size
        }
    }

    override fun onBindViewHolder(holder: AnimeHolder, position: Int) {
        val anime = animeList[position]
        val binding = holder.binding

        binding.textViewRV.text = anime.name
        binding.cardViewLine.setOnClickListener {
            val intent = Intent(context, AnimeActivity::class.java)
            intent.putExtra("info", "old")
            intent.putExtra("id", anime.id)
            context.startActivity(intent)
        }
    }

}