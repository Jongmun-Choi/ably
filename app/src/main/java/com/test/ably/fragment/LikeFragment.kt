package com.test.ably.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.test.ably.MainActivity
import com.test.ably.databinding.FragmentLikeBinding
import com.test.ably.di.factory.MainViewModelFactory
import com.test.ably.viewmodel.MainViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class LikeFragment : DaggerFragment() {

    @Inject
    lateinit var factory : MainViewModelFactory

    lateinit var binding : FragmentLikeBinding
    lateinit var viewModel : MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLikeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(activity as MainActivity, factory).get(MainViewModel::class.java)
        return binding.root
    }
}