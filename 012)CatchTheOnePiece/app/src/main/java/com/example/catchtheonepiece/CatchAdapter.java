package com.example.catchtheonepiece;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catchtheonepiece.databinding.CardDesignBinding;

import java.util.List;

public class CatchAdapter extends RecyclerView.Adapter<CatchAdapter.CardViewDesignObjectsHolder>{
    private Context mContext;
    private List<Character> onePiecesList;

    public CatchAdapter(Context mContext, List<Character> onePiecesList) {
        this.mContext = mContext;
        this.onePiecesList = onePiecesList;
    }

    @NonNull
    @Override
    public CardViewDesignObjectsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        CardDesignBinding design = DataBindingUtil.inflate(layoutInflater, R.layout.card_design, parent, false);
        return new CardViewDesignObjectsHolder(design);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewDesignObjectsHolder holder, int position) {
        Character character = onePiecesList.get(position);
        CardDesignBinding design = holder.design;
        design.textViewCatch.setText(character.getCharacterName());
        design.cardViewLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CatchActivity.class);
                intent.putExtra("character", character);
                mContext.startActivity(intent);
            }
        });
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
        private CardDesignBinding design;

        public CardViewDesignObjectsHolder(CardDesignBinding design) {
            super(design.getRoot());
            this.design = design;
        }
    }
}
