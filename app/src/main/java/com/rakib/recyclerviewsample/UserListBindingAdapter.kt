package com.rakib.recyclerviewsample

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("progressbarStatus")
fun showHideProgressbar(progressBar: ProgressBar, userStatus: UserStatus?) {
    when (userStatus) {
        UserStatus.LOADING -> {
            progressBar.visibility = View.VISIBLE
        }
        UserStatus.DONE -> {
            progressBar.visibility = View.INVISIBLE
        }
        UserStatus.ERROR->{
            progressBar.visibility = View.VISIBLE
        }
        else ->
            progressBar.visibility = View.VISIBLE
    }
}

@BindingAdapter("visibility", "count")
fun bindResultCount(textView: TextView, userStatus: UserStatus?, count : Int){
    when (userStatus) {
        UserStatus.LOADING -> {
            textView.visibility = View.INVISIBLE
        }
        UserStatus.DONE -> {
            textView.visibility = View.VISIBLE
            textView.text = count.toString()
        }
        UserStatus.ERROR->{
            textView.visibility = View.INVISIBLE
        }
        else ->
            textView.visibility = View.INVISIBLE
    }

}

@BindingAdapter("list")
fun RecyclerView.bindRecyclerView(data: List<User>?) {
    val adapter = this.adapter as UserAdapter
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