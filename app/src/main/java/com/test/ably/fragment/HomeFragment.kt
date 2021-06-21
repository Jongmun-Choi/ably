package com.test.ably.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.test.ably.MainActivity
import com.test.ably.adapter.BannerPagerAdapter
import com.test.ably.databinding.FragmentHomeBinding
import com.test.ably.di.factory.MainViewModelFactory
import com.test.ably.model.Banner
import com.test.ably.viewmodel.MainViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class HomeFragment : DaggerFragment() {

    @Inject lateinit var factory : MainViewModelFactory

    lateinit var binding : FragmentHomeBinding
    lateinit var viewModel : MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(activity as MainActivity, factory).get(MainViewModel::class.java)

        binding.bannerPager.adapter = BannerPagerAdapter(activity as MainActivity, listOf<Banner>())

        viewModel.banners.observe(activity as MainActivity, object : Observer<List<Banner>> {
            override fun onChanged(t: List<Banner>?) {
                if(t != null) {
                    binding.bannerPager.adapter = BannerPagerAdapter(activity as MainActivity,t)
                }
            }
        })

        viewModel.loadHomeData()

        return binding.root
    }
}