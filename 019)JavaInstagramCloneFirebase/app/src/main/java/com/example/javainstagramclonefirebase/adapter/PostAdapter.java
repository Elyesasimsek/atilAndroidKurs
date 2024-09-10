package com.example.javainstagramclonefirebase.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javainstagramclonefirebase.R;
import com.example.javainstagramclonefirebase.databinding.RecyclerRowBinding;
import com.example.javainstagramclonefirebase.model.Post;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostHolder>{
    private Context mContext;
    private List<Post> postList;

    public PostAdapter(Context mContext, List<Post> postList) {
        this.mContext = mContext;
        this.postList = postList;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        RecyclerRowBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.recycler_row, parent, false);
        return new PostHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        Post post =postList.get(position);
        RecyclerRowBinding binding = holder.binding;
        binding.textViewRVUserEmail.setText(post.getEmail());
        binding.textViewRVComment.setText(post.getComment());
        Picasso.get().load(post.getDownloadUrl()).into(binding.imageViewRV);
    }

    @Override
    public int getItemCount() {
        if (postList == null){
            return 0;
        }else {
            return postList.size();
        }
    }

    public class PostHolder extends RecyclerView.ViewHolder{
        private RecyclerRowBinding binding;

        public PostHolder(RecyclerRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
