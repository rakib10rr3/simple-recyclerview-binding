package com.rakib.recyclerviewsample


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.rakib.recyclerviewsample.databinding.FragmentUserDetailsBinding

/**
 * A simple [Fragment] subclass.
 */
class UserDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application = requireNotNull(activity).application
        val binding = FragmentUserDetailsBinding.inflate(inflater)

        binding.lifecycleOwner = this

        val user = UserDetailsFragmentArgs.fromBundle(arguments!!).user

        val viewModelFactory = UserDetailViewModelFactory(user,application)

        binding.detailsViewModel = ViewModelProviders.of(this,viewModelFactory).get(UserDetailViewModel::class.java)

        return binding.root
    }


}
