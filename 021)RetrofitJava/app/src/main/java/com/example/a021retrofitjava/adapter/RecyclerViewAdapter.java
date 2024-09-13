package com.example.a021retrofitjava.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a021retrofitjava.R;
import com.example.a021retrofitjava.databinding.RowLayoutBinding;
import com.example.a021retrofitjava.model.CryptoModel;
import com.example.a021retrofitjava.service.CryptoAPI;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>{
    private Context mContext;
    private ArrayList<CryptoModel> cryptoModelList;
    String[] colors = new String[] {"#FF0000", "#00FF00", "#0000FF", "#FFFF00", "#00FFFF", "#FF00FF", "#C0C0C0", "#808080"};
    public RecyclerViewAdapter(Context mContext, ArrayList<CryptoModel> cryptoModelList) {
        this.mContext = mContext;
        this.cryptoModelList = cryptoModelList;
    }

    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        RowLayoutBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.row_layout, parent, false);
        return new RowHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {
        CryptoModel cryptoModel = cryptoModelList.get(position);
        RowLayoutBinding binding = holder.binding;
        binding.textViewRVName.setText(cryptoModel.getCurrency());
        binding.textViewRVPrice.setText(cryptoModel.getPrice());
        int color = Color.parseColor(colors[position % colors.length]);
        binding.cardViewRV.setCardBackgroundColor(color);
    }

    @Override
    public int getItemCount() {
        if (cryptoModelList == null){
            return 0;
        }else {
            return cryptoModelList.size();
        }
    }

    public class RowHolder extends RecyclerView.ViewHolder {
        private RowLayoutBinding binding;

        public RowHolder(RowLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
