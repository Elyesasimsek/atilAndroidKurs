package com.elyesasimsek.kotlinmaps.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.elyesasimsek.kotlinmaps.R
import com.elyesasimsek.kotlinmaps.databinding.RecyclerRowBinding
import com.elyesasimsek.kotlinmaps.model.Place
import com.elyesasimsek.kotlinmaps.view.MapsActivity

class PlaceAdapter (
    private var context: Context,
    private var placeList: List<Place>
): RecyclerView.Adapter<PlaceAdapter.PlaceHolder>(){
    class PlaceHolder(val binding: RecyclerRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding = DataBindingUtil.inflate<RecyclerRowBinding>(layoutInflater,R.layout.recycler_row, parent, false)
        return PlaceHolder(binding)
    }

    override fun getItemCount(): Int {
        if (placeList == null){
            return 0;
        }else{
            return placeList.size
        }
    }

    override fun onBindViewHolder(holder: PlaceHolder, position: Int) {
        val place = placeList[position]
        val binding = holder.binding

        binding.textViewRV.text = place.name
        binding.cardView.setOnClickListener {
            val intent = Intent(context, MapsActivity::class.java)
            intent.putExtra("selectedPlace", place)
            intent.putExtra("info", "old")
            context.startActivity(intent)
        }
    }


}