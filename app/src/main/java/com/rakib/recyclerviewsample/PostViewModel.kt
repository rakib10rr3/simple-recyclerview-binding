package com.rakib.recyclerviewsample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rakib.recyclerviewsample.API.UserApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class PostViewModel : ViewModel() {

    private var postList: List<Post>? = null

    private val _status = MutableLiveData<Status>()
    val status : LiveData<Status>
        get() = _status

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>>?
        get() = _posts

    init {
        getPosts()
    }


    fun getPosts() {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    postList = UserApi.retrofitService.getPosts()
                }
                _status.value = Status.LOADING
                _posts.value = postList

                Timber.i("${postList?.size}")
                _status.value = Status.DONE
            } catch (e: Exception) {
                e.printStackTrace()
                _status.value = Status.ERROR
                _posts.value = ArrayList()
            }
        }
    }
}