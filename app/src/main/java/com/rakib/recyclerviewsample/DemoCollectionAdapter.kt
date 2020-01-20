package com.rakib.recyclerviewsample

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

const val ARG_OBJECT = "object"

class DemoCollectionAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return TAB_TITLES.size
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> PostListFragment()
            1-> TestFragment()
            else -> PostListFragment()
        }
    }

    fun getPageTitle(position: Int) : String
    {
        return TAB_TITLES[position]
    }

    companion object {
        private val TAB_TITLES = arrayOf(
            "Posts",
            "Tests"
        )
    }
}