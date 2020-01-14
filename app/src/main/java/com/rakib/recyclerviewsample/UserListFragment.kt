package com.rakib.recyclerviewsample


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.rakib.recyclerviewsample.databinding.FragmentUserListBinding

/**
 * A simple [Fragment] subclass.
 */
class UserListFragment : Fragment() {

    lateinit var viewModel: UserListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding = FragmentUserListBinding.inflate(inflater)

        binding.lifecycleOwner = this

        viewModel = ViewModelProviders.of(this).get(UserListViewModel::class.java)

        binding.userListViewModel = viewModel

        binding.userRV.adapter = UserAdapter()

        return binding.root
    }


}
