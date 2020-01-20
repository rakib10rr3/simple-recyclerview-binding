package com.rakib.recyclerviewsample

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("progressbarStatus")
fun showHideProgressbar(progressBar: ProgressBar, userStatus: Status?) {
    when (userStatus) {
        Status.LOADING -> {
            progressBar.visibility = View.VISIBLE
        }
        Status.DONE -> {
            progressBar.visibility = View.INVISIBLE
        }
        Status.ERROR->{
            progressBar.visibility = View.VISIBLE
        }
        else ->
            progressBar.visibility = View.VISIBLE
    }
}

@BindingAdapter("visibility", "count")
fun bindResultCount(textView: TextView, userStatus: Status?, count : Int){
    when (userStatus) {
        Status.LOADING -> {
            textView.visibility = View.INVISIBLE
        }
        Status.DONE -> {
            textView.visibility = View.VISIBLE
            textView.text = count.toString()
        }
        Status.ERROR->{
            textView.visibility = View.INVISIBLE
        }
        else ->
            textView.visibility = View.INVISIBLE
    }

}

@BindingAdapter("list")
fun bindUsersRecyclerView(recyclerView: RecyclerView, data: List<User>?) {
    val adapter = recyclerView.adapter as UserAdapter
    data?.let {
        adapter.submitList(data)
    }
}


@BindingAdapter("postList")
fun bindPostsRecyclerView(recyclerView: RecyclerView, data: List<Post>?) {
    val adapter = recyclerView.adapter as PostAdapter
    data?.let {
        adapter.submitList(data)
    }
}

//@BindingAdapter("resultStatus")
//fun showCount(progressBar: ProgressBar, textView: TextView, userStatus: UserStatus?, var  count : Int = 0){
//
//}

@BindingAdapter("name")
fun bindName(textView: TextView, user: User) {
    textView.text = user.name
}

@BindingAdapter("username")
fun bindUserName(textView: TextView, user: User) {
    textView.text = user.username
}

@BindingAdapter("email")
fun bindEmail(textView: TextView, user: User) {
    textView.text = user.email
}