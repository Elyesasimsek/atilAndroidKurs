package com.example.javamaps.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javamaps.R;
import com.example.javamaps.databinding.RecyclerRowBinding;
import com.example.javamaps.model.Place;
import com.example.javamaps.view.MapsActivity;

import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceHolder>{
    private Context mContext;
    private List<Place> placeList;


    public PlaceAdapter(Context mContext, List<Place> placeList) {
        this.mContext = mContext;
        this.placeList = placeList;
    }

    @NonNull
    @Override
    public PlaceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        RecyclerRowBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.recycler_row, parent, false);
        return new PlaceHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceHolder holder, int position) {
        Place place = placeList.get(position);
        RecyclerRowBinding binding = holder.binding;
        binding.textViewRV.setText(place.getName());
        binding.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MapsActivity.class);
                intent.putExtra("info", "old");
                intent.putExtra("place", place);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (placeList == null){
            return 0;
        }else {
            return placeList.size();
        }
    }

    public class PlaceHolder extends RecyclerView.ViewHolder{
        RecyclerRowBinding binding;

        public PlaceHolder(RecyclerRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
