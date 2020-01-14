package com.rakib.recyclerviewsample

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.lang.Exception


class UserListViewModel : ViewModel() {

    lateinit var userList: List<User>

    private val _status = MutableLiveData<UserStatus>()
    val status: LiveData<UserStatus>
        get() = _status

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>>?
        get() = _users

    init {
        getUser()
    }


    fun getUser() {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    userList = UserApi.retrofitService.getUsers()
                }
                _status.value = UserStatus.LOADING
                _users.value = userList
                Timber.i("$userList")
                _status.value = UserStatus.DONE
            } catch (e: Exception) {
                e.printStackTrace()
                _status.value = UserStatus.ERROR
                _users.value = ArrayList()
            }
        }
    }

}

enum class UserStatus { LOADING, ERROR, DONE }