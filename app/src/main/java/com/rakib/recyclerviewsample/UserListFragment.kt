package com.rakib.recyclerviewsample


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
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

        binding.userRV.adapter = UserAdapter(UserListener {
            //user -> Toast.makeText(context,"${user?.address?.city}", Toast.LENGTH_SHORT).show()
            viewModel.displayUserDetails(it)
        })

        viewModel.navigateToUserDetails.observe(this, Observer {
                it?.let {
                    findNavController().navigate(UserListFragmentDirections.actionUserListFragmentToUserDetailsFragment(it))
                    viewModel.displayUserDetailsComplete()
                }
            }
        )

        binding.btn.setOnClickListener {
            view: View? ->  view?.findNavController()?.navigate(R.id.viewPagerFragment)
        }

        return binding.root
    }


}
