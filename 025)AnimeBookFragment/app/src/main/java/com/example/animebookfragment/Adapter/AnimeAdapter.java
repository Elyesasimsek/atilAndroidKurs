package com.example.animebookfragment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animebookfragment.Model.Anime;
import com.example.animebookfragment.R;
import com.example.animebookfragment.View.MainFragment;
import com.example.animebookfragment.View.MainFragmentDirections;
import com.example.animebookfragment.databinding.RecyclerRowBinding;

import java.util.List;

public class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.AnimeHolder>{
    private Context context;
    private List<Anime> animeList;

    public AnimeAdapter(Context context, List<Anime> animeList) {
        this.context = context;
        this.animeList = animeList;
    }

    @NonNull
    @Override
    public AnimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        RecyclerRowBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.recycler_row, parent, false);
        return new AnimeHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimeHolder holder, int position) {
        Anime anime = animeList.get(position);
        RecyclerRowBinding binding = holder.binding;
        binding.textView.setText(anime.getName());
        binding.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainFragmentDirections.ActionMainFragmentToAnimeFragment action = MainFragmentDirections.actionMainFragmentToAnimeFragment("old");
                action.setInfo("old");
                action.setAnimeId(anime.getId());
                Navigation.findNavController(v).navigate(action);
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

    public class AnimeHolder extends RecyclerView.ViewHolder {
        private RecyclerRowBinding binding;

        public AnimeHolder(RecyclerRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
