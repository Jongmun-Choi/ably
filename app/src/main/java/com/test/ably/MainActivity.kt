package com.test.ably

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.test.ably.adapter.FragmentAdapter
import com.test.ably.databinding.ActivityMainBinding
import com.test.ably.di.factory.MainViewModelFactory
import com.test.ably.fragment.HomeFragment
import com.test.ably.fragment.LikeFragment
import com.test.ably.viewmodel.MainViewModel
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    private lateinit var binding :ActivityMainBinding
    private val fragmentList = arrayListOf<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        val adapter = FragmentAdapter(supportFragmentManager, this.lifecycle)
        binding.container.adapter = adapter
        TabLayoutMediator(binding.tabs, binding.container) { tab, position ->
            when(position) {
                0 -> {
                    tab.setIcon(R.drawable.icon_home_active).setText("홈")
                }
                1 -> {
                    tab.setIcon(R.drawable.icon_zzim).setText("좋아요")
                }
            }
        }.attach()

        binding.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab.let {
                    val position = it!!.position
                    when(position){
                        0 -> tab!!.setIcon(R.drawable.icon_home_active).setText("홈")
                        1 -> tab!!.setIcon(R.drawable.icon_zzim_active).setText("좋아요")
                        else -> {}
                    }
                    binding.container.setCurrentItem(position, true)
                }

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab.let {
                    val position = it!!.position
                    when(position){
                        0 -> tab!!.setIcon(R.drawable.icon_home).setText("홈")
                        1 -> tab!!.setIcon(R.drawable.icon_zzim).setText("좋아요")
                        else -> {}
                    }
                }

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }
}