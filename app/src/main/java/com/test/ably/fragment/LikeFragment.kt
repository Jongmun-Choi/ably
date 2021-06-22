package com.test.ably.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.test.ably.MainActivity
import com.test.ably.R
import com.test.ably.adapter.GoodsAdapter
import com.test.ably.databinding.FragmentLikeBinding
import com.test.ably.di.factory.MainViewModelFactory
import com.test.ably.model.Goods
import com.test.ably.util.GridSpacingItemDecoration
import com.test.ably.viewmodel.MainViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class LikeFragment : DaggerFragment(), View.OnClickListener {

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

        binding.lifecycleOwner = activity as MainActivity

        viewModel = ViewModelProvider(activity as MainActivity, factory).get(MainViewModel::class.java)

        var adapter = GoodsAdapter(this)

        binding.rvLike.adapter = adapter

        val spanCount = 2
        val spacing = 50
        val inCludeEdge = false
        binding.rvLike.addItemDecoration(GridSpacingItemDecoration(spanCount, spacing, inCludeEdge))

        viewModel.goods.observe(activity as MainActivity){
            adapter.submitList(it.filter { it.like == true }.toMutableList())
        }


        return binding.root
    }

    override fun onClick(v: View?) {
        if(v != null) {
            when (v.id) {
                R.id.iv_like -> {
                    val item = v.tag as Goods
                    viewModel.setLikeData(item.id)
                }
            }
        }
    }

}