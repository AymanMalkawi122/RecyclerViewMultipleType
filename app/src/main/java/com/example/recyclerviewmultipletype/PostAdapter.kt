package com.example.recyclerviewmultipletype

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.recyclerviewmultipletype.databinding.PostElementBinding
import com.example.recyclerviewmultipletype.databinding.SideScrollerElementBinding

class PostAdapter(val linearLayoutManager: LinearLayoutManager) :
    RecyclerView.Adapter<ViewHoldersPostList>() {

    private val imageDiffCallBack = object : DiffUtil.ItemCallback<Image>() {
        override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem == newItem
        }
    }

    private val postDiffCallBack = object : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }
    }

    private val sideScrollAdapter = SideScrollAdapter()
    private val postDiffer = AsyncListDiffer(this, postDiffCallBack)
    private val imageDiffer = AsyncListDiffer(this, imageDiffCallBack)

    var images: List<Image>
        get() = imageDiffer.currentList
        set(value) = imageDiffer.submitList(value)

    var posts: List<Post>
        get() = postDiffer.currentList
        set(value) = postDiffer.submitList(value)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHoldersPostList {
        return when (viewType) {
            R.layout.post_element -> {
                ViewHoldersPostList.PostViewHolder(
                    PostElementBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            R.layout.side_scroller_element -> {
                ViewHoldersPostList.SideScrollerViewHolder(
                    SideScrollerElementBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            else -> throw IllegalArgumentException("Wrong View Holder Provided")
        }
    }

    override fun getItemCount() = posts.size

    override fun getItemViewType(position: Int): Int {
        return if (((position % 10) == 0) and (position > 0)) R.layout.side_scroller_element else R.layout.post_element
    }

    override fun onBindViewHolder(holder: ViewHoldersPostList, position: Int) {
        val currentItem = posts[position]
        when (holder) {
            is ViewHoldersPostList.PostViewHolder -> holder.bind(currentItem)
            is ViewHoldersPostList.SideScrollerViewHolder -> holder.bind(images, sideScrollAdapter, linearLayoutManager)
        }
    }
}