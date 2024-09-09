package com.example.animebook;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animebook.databinding.RecyclerRowBinding;

import java.util.List;

public class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.AnimeHolder>{
    private Context mContext;
    private List<Anime> animeList;

    public AnimeAdapter(Context mContext, List<Anime> animeList) {
        this.mContext = mContext;
        this.animeList = animeList;
    }

    @NonNull
    @Override
    public AnimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        RecyclerRowBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.recycler_row, parent, false);
        return new AnimeHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimeHolder holder, int position) {
        Anime anime = animeList.get(position);
        RecyclerRowBinding binding = holder.binding;
        binding.textViewRV.setText(anime.getName());
        binding.cardViewLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AnimeActivity.class);
                intent.putExtra("info", "old");
                intent.putExtra("animeId", anime.getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (animeList == null){
            return 0;
        }else {
            return animeList.size();
        }
    }

    public class AnimeHolder extends RecyclerView.ViewHolder{
        private RecyclerRowBinding binding;

        public AnimeHolder(RecyclerRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
