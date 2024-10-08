package com.elyesasimsek.landmarkbookkotlin

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.elyesasimsek.landmarkbookkotlin.databinding.RecyclerRowBinding

class LandmarkAdaptor(
    private var context: Context,
    private var landmarkList: List<Landmark>
) : RecyclerView.Adapter<LandmarkAdaptor.LandmarkHolder>() {

    class LandmarkHolder(val binding: RecyclerRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LandmarkHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding = DataBindingUtil.inflate<RecyclerRowBinding>(layoutInflater, R.layout.recycler_row, parent, false)
        return LandmarkHolder(binding)
    }

    override fun getItemCount(): Int {
        if (landmarkList == null){
            return 0;
        }else{
            return landmarkList.size
        }
    }

    override fun onBindViewHolder(holder: LandmarkHolder, position: Int) {
        val landmark = landmarkList[position]
        val binding = holder.binding

        binding.textView.text = landmark.name
        binding.cardView.setOnClickListener {
            val intent = Intent(context, DetailsActivity::class.java)
            //intent.putExtra("landmark", landmark)
            MySingleton.chosenLandmark = landmark
            context.startActivity(intent)
        }
    }
}