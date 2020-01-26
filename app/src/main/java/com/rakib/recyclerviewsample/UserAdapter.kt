package com.rakib.recyclerviewsample

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rakib.recyclerviewsample.databinding.ListItemUserBinding

class UserAdapter(val context: Context, val clickListener: UserListener) :
    ListAdapter<User, UserAdapter.UserViewHolder>(DiffUserCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder.from(parent)
    }

    private var selectedItemViewHolder: UserViewHolder? = null

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user, UserListener {

            // Deselect last selected item
            selectedItemViewHolder?.apply {

                deselect(context)

            }

            // Select current item
            holder.select(context)

            // Save current item to variable
            selectedItemViewHolder = holder


            // Call the other click listeners
            clickListener.onClick(it)
        })
    }


    companion object DiffUserCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

    }


    class UserViewHolder(private var binding: ListItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {


        companion object {

            fun from(parent: ViewGroup): UserViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemUserBinding.inflate(layoutInflater, parent, false)
                return UserViewHolder(binding)
            }
        }

        fun select(
            context: Context
        ) {
            binding.container.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.colorPrimary
                )
            )
        }

        fun deselect(
            context: Context
        ) {
            binding.container.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.white
                )
            )
        }

        fun bind(
            user: User,
            clickListener: UserListener
        ) {
            binding.user = user
            binding.executePendingBindings()
            binding.clickListener = clickListener
        }
    }
}

class UserListener(val clickListener: (userId: User) -> Unit) {
    fun onClick(user: User) = clickListener(user)
}

