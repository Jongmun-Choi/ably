package com.test.ably

import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        setSupportActionBar(binding.toolbar)

        val adapter = FragmentAdapter(supportFragmentManager, this.lifecycle)
        binding.container.adapter = adapter

        TabLayoutMediator(binding.tabs, binding.container) { tab, position ->
            when(position) {
                0 -> {
                    tab.setIcon(R.drawable.icon_home_active).setText("홈")
                    tab!!.icon.let { it!!.setTint(resources.getColor(R.color.watermelon, null))  }
                    title = "홈"
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
                        0 -> {
                            this@MainActivity.title = "홈"
                            tab!!.setIcon(R.drawable.icon_home_active).setText("홈")
                            val tintColor = ContextCompat.getColor(this@MainActivity, R.color.watermelon);
                        }
                        1 -> {
                            this@MainActivity.title = "좋아요"
                            tab!!.setIcon(R.drawable.icon_zzim_active).setText("좋아요")
                        }
                        else -> {}
                    }
                    tab!!.icon.let { it!!.setTint(resources.getColor(R.color.watermelon, null))  }
                }

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab.let {
                    tab!!.icon.let { it!!.setTint(resources.getColor(R.color.brownish_grey, null))  }
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