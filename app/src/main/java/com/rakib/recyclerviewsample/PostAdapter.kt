package com.rakib.recyclerviewsample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rakib.recyclerviewsample.databinding.ListItemPostBinding
import com.rakib.recyclerviewsample.databinding.ListItemUserBinding

class PostAdapter :  ListAdapter<Post,PostAdapter.PostViewHolder>(DiffPostCallback){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder.from(parent)
    }


    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }


    companion object DiffPostCallback : DiffUtil.ItemCallback<Post>(){
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }

    }


    class PostViewHolder(private var binding: ListItemPostBinding) : RecyclerView.ViewHolder(binding.root){

        companion object {
            fun from(parent: ViewGroup) : PostViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemPostBinding.inflate(layoutInflater,parent,false)
                return PostViewHolder(binding)
            }
        }

        fun bind(
            post: Post
            //clickListener: PostListenerListener
        ){
            binding.post = post
            binding.executePendingBindings()
            //binding.clickListener = clickListener
        }
    }
}

//class PostListenerListener(val clickListener : (post : Post) -> Unit){
//    fun onClick(post: Post) = clickListener(post)
//}

