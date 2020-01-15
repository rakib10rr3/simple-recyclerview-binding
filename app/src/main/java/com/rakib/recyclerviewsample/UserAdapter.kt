package com.rakib.recyclerviewsample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rakib.recyclerviewsample.databinding.ListItemUserBinding

class UserAdapter(val clickListener: UserListener) :  ListAdapter<User,UserAdapter.UserViewHolder>(DiffUserCallback){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder.from(parent)
    }


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user,clickListener)
    }


    companion object DiffUserCallback : DiffUtil.ItemCallback<User>(){
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

    }


    class UserViewHolder(private var binding: ListItemUserBinding) : RecyclerView.ViewHolder(binding.root){

        companion object {
            fun from(parent: ViewGroup) : UserViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemUserBinding.inflate(layoutInflater,parent,false)
                return UserViewHolder(binding)
            }
        }

        fun bind(
            user: User,
            clickListener: UserListener
        ){
            binding.user = user
            binding.executePendingBindings()
            binding.clickListener = clickListener
        }
    }
}

class UserListener(val clickListener : (userId : User) -> Unit){
    fun onClick(user: User) = clickListener(user)
}

