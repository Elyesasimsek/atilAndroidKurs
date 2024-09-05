package com.example.catchtheonepiece;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CatchAdapter extends RecyclerView.Adapter<CatchAdapter.CardViewDesignObjectsHolder>{
    private Context mContext;
    private List<String> onePiecesList;

    public CatchAdapter(Context mContext, List<String> onePiecesList) {
        this.mContext = mContext;
        this.onePiecesList = onePiecesList;
    }

    @NonNull
    @Override
    public CardViewDesignObjectsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_design, parent, false);
        return new CardViewDesignObjectsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewDesignObjectsHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if (onePiecesList == null){
            return 0;
        }else {
            return onePiecesList.size();
        }
    }

    public class CardViewDesignObjectsHolder extends RecyclerView.ViewHolder{
        private TextView textViewCatch;

        public CardViewDesignObjectsHolder(@NonNull View itemView) {
            super(itemView);
            textViewCatch = itemView.findViewById(R.id.textViewCatch);
        }
    }
}
