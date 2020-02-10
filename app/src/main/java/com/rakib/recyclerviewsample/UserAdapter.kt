package com.rakib.recyclerviewsample

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rakib.recyclerviewsample.databinding.ListItemUserBinding
import timber.log.Timber

class UserAdapter(
    private val context: Context,
    private val lifecycleOwner: LifecycleOwner,
    private val clickListener: UserListener
) :
    ListAdapter<User, UserAdapter.UserViewHolder>(DiffUserCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder.from(parent)
    }

    private val selectedItemIdLive: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(
            context,
            lifecycleOwner,
            selectedItemIdLive,
            user,
            UserListener {

                // Save current item to variable
                selectedItemIdLive.value = user.id ?: 0

                // Call the other click listeners
                clickListener.onClick(it)

            }
        )
    }


    companion object DiffUserCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
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

        private fun select(
            context: Context
        ) {
            binding.container.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.colorPrimary
                )
            )
        }

        private fun deselect(
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
            context: Context,
            lifecycleOwner: LifecycleOwner,
            liveData: LiveData<Int>,
            user: User,
            clickListener: UserListener
        ) {
            binding.user = user
            binding.executePendingBindings()
            binding.clickListener = clickListener

            liveData.observe(lifecycleOwner, Observer {

                if (it == user.id) {
                    select(context)
                } else {
                    deselect(context)
                }

            })
        }
    }
}

class UserListener(val clickListener: (userId: User) -> Unit) {
    fun onClick(user: User) = clickListener(user)
}

