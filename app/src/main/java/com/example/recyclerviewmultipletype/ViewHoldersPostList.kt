package com.example.recyclerviewmultipletype

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.recyclerviewmultipletype.databinding.PostElementBinding
import com.example.recyclerviewmultipletype.databinding.SideScrollerElementBinding


sealed class ViewHoldersPostList(binding: ViewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    class PostViewHolder(val binding: PostElementBinding):ViewHoldersPostList(binding){
        fun bind(post: Post){
            binding.postContent.text = post.body
            binding.postTitle.text = post.title
        }
    }

    class SideScrollerViewHolder(val binding: SideScrollerElementBinding):ViewHoldersPostList(binding){
        fun bind(imageList: List<Image>, sideScrollAdapter: SideScrollAdapter, sideScrollLayoutManager: LinearLayoutManager){
            sideScrollAdapter.images = imageList
            binding.imageList.apply {
                layoutManager = sideScrollLayoutManager
                adapter = sideScrollAdapter
            }
        }
    }
}