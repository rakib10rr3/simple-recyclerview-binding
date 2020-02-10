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


class UserListViewModel : ViewModel() {

    private var userList: List<User>? = null

    private val _status = MutableLiveData<Status>()
    val status: LiveData<Status>
        get() = _status

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>>?
        get() = _users

    private val _navigateToUserDetails = MutableLiveData<User>()
    val navigateToUserDetails: LiveData<User>
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

                var users = mutableListOf<User>()

                // note: just making the list big
//                userList?.apply {
//                    users.addAll(this.toTypedArray().copyOf())
//                }

                for (i in 1..50) {
                    users.add(
                        User(
                            id = i,
                            name = "aaa",
                            username = "bbb",
                            email = "ccc@ddd.eee"
                        )
                    )
                }

                _status.value = Status.LOADING
//                _users.value = userList
                _users.value = users
//                Timber.i("$userList")
                Timber.i("${userList?.size}")
                _status.value = Status.DONE
            } catch (e: Exception) {
                e.printStackTrace()
                _status.value = Status.ERROR
                _users.value = ArrayList()
            }
        }
    }

    fun displayUserDetails(user: User) {
        _navigateToUserDetails.value = user
    }

    fun displayUserDetailsComplete() {
        _navigateToUserDetails.value = null
    }

    fun go() {
//        Activity.findNavController().navigate(R.id.userDetailsFragment)
    }

}

//enum class UserStatus { LOADING, ERROR, DONE }