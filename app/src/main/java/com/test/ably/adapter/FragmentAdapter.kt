package com.test.ably.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.test.ably.fragment.HomeFragment
import com.test.ably.fragment.LikeFragment

class FragmentAdapter(fragmentManager : FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {

    val fragmentList = mutableListOf<Fragment>()

    init {
        fragmentList.add(HomeFragment())
        fragmentList.add(LikeFragment())
    }

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList.get(position)
    }
}