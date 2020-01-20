package com.rakib.recyclerviewsample


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.rakib.recyclerviewsample.databinding.FragmentPostListBinding

/**
 * A simple [Fragment] subclass.
 */
class PostListFragment : Fragment() {

    companion object {

        private const val ARG_SECTION_NUMBER = "section_number"
        @JvmStatic
        fun newInstance(sectionNumber: Int): PostListFragment {
            return PostListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

    lateinit var viewModel: PostViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentPostListBinding.inflate(inflater)

        binding.lifecycleOwner = this

        viewModel = ViewModelProviders.of(this).get(PostViewModel::class.java)

        binding.postListViewModel = viewModel

//        binding.userRV.adapter = UserAdapter(UserListener {
//            //user -> Toast.makeText(context,"${user?.address?.city}", Toast.LENGTH_SHORT).show()
//            viewModel.displayUserDetails(it)
//        })

        binding.postRV.adapter = PostAdapter()

        return binding.root
    }


}
