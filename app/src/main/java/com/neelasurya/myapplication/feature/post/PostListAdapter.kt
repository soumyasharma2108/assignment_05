package com.neelasurya.myapplication.feature.post

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.neelasurya.myapplication.R
import com.neelasurya.myapplication.databinding.ItemPostBinding
import com.neelasurya.myapplication.model.Order


class PostListAdapter(private val postDao: DatabaseReference) : RecyclerView.Adapter<PostListAdapter.ViewHolder>() {
    private lateinit var postList: List<Order>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemPostBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_post, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(postList[position], position, postDao)
    }

    override fun getItemCount(): Int {
        return if (::postList.isInitialized) postList.size else 0
    }

    fun updatePostList(list:List<Order>) {
        postList = list
        notifyDataSetChanged()
    }
    
    inner class ViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        private lateinit var viewModel: PostViewModel
        fun bind(post: Order, position: Int, postDao: DatabaseReference) {
            viewModel = PostViewModel(postDao)
            viewModel.bind(post)
            binding.viewModel = viewModel
            binding.position = position
            binding.callback = this@PostListAdapter
            binding.executePendingBindings()
        }

    }

    fun onRowClick(view: View?, position: Int, viewModel: PostViewModel) {
        viewModel . onExpandedClick(postList[position])
        notifyItemChanged(position)
    }
    fun onCancelClick(view: View?, position: Int, viewModel: PostViewModel) {
        viewModel . onCancelClick(postList[position])
        notifyItemChanged(position)
    }
    fun onButtonClickClick(view: View?, position: Int, viewModel: PostViewModel) {
        viewModel . onButtonClickClick(postList[position])
        notifyItemChanged(position)
    }

}
