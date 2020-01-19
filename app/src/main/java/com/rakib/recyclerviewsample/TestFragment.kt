package com.rakib.recyclerviewsample


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_test.*

/**
 * A simple [Fragment] subclass.
 */
class TestFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val userListViewModel = ViewModelProviders.of(this).get(UserListViewModel::class.java)

        userListViewModel.users?.observe(this, Observer {users ->
            result_tv.text = "${users?.size}"
        })

        return inflater.inflate(R.layout.fragment_test, container, false)
    }


}
