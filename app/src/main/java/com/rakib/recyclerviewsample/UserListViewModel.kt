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
import java.lang.Exception


class UserListViewModel : ViewModel() {

    private var userList: List<User>? = null

    private val _status = MutableLiveData<UserStatus>()
    val status: LiveData<UserStatus>
        get() = _status

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>>?
        get() = _users

    private val _navigateToUserDetails = MutableLiveData<User>()
    val navigateToUserDetails : LiveData<User>
        get() = _navigateToUserDetails

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

    fun displayUserDetails(user: User)
    {
        _navigateToUserDetails.value = user
    }

    fun displayUserDetailsComplete(){
        _navigateToUserDetails.value = null
    }

}

enum class UserStatus { LOADING, ERROR, DONE }