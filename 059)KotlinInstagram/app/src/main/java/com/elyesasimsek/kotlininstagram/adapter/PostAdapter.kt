package com.elyesasimsek.kotlininstagram.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.elyesasimsek.kotlininstagram.R
import com.elyesasimsek.kotlininstagram.databinding.RecyclerRowBinding
import com.elyesasimsek.kotlininstagram.model.Post
import com.squareup.picasso.Picasso

class PostAdapter (
    private var context: Context,
    private var postList: List<Post>
): RecyclerView.Adapter<PostAdapter.postHolder>() {
    class postHolder(val binding: RecyclerRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): postHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding = DataBindingUtil.inflate<RecyclerRowBinding>(layoutInflater, R.layout.recycler_row, parent, false)
        return postHolder(binding)
    }

    override fun getItemCount(): Int {
        if (postList == null){
            return 0;
        }else{
            return postList.size
        }
    }

    override fun onBindViewHolder(holder: postHolder, position: Int) {
        val post = postList[position]
        val binding = holder.binding

        binding.textViewRVUserEmail.text = post.email
        binding.textViewRVComment.text = post.comment
        Picasso.get().load(post.downloadUrl).into(binding.imageViewRV)

    }
}