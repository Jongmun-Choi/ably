package com.test.ably

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
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

        fragmentList.add(HomeFragment())
        fragmentList.add(LikeFragment())

        supportFragmentManager.beginTransaction().add(binding.container.id, fragmentList.get(0)).commitAllowingStateLoss()

        binding.tabs.addTab(binding.tabs.newTab().setText("홈"))
        binding.tabs.addTab(binding.tabs.newTab().setText("좋아요"))

        binding.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if(tab != null) {
                    supportFragmentManager.beginTransaction().replace(binding.container.id, fragmentList.get(tab.position)).commit()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }
}