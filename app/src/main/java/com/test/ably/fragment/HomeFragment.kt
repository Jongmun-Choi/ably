package com.test.ably.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.test.ably.MainActivity
import com.test.ably.R
import com.test.ably.adapter.BannerPagerAdapter
import com.test.ably.adapter.GoodsAdapter
import com.test.ably.databinding.FragmentHomeBinding
import com.test.ably.di.factory.MainViewModelFactory
import com.test.ably.model.Banner
import com.test.ably.model.Goods
import com.test.ably.util.GridSpacingItemDecoration
import com.test.ably.viewmodel.MainViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class HomeFragment : DaggerFragment(), View.OnClickListener {

    @Inject lateinit var factory : MainViewModelFactory

    lateinit var binding : FragmentHomeBinding
    lateinit var viewModel : MainViewModel
    var currentPage = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = activity as MainActivity

        viewModel = ViewModelProvider(activity as MainActivity, factory).get(MainViewModel::class.java)

        binding.bannerPager.adapter = BannerPagerAdapter(activity as MainActivity, listOf<Banner>())

        binding.bannerPager.setOnTouchListener(object : View.OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if (event!!.action === MotionEvent.ACTION_DOWN &&
                    v is ViewGroup
                ) {
                    v.requestDisallowInterceptTouchEvent(true)
                }
                return false
            }
        })

        binding.bannerPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                binding.currentPage = position+1
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

        val adapter = GoodsAdapter(this)
        binding.goodsGrid.adapter = adapter
        val spanCount = 2
        val spacing = 50
        val inCludeEdge = false

        binding.goodsGrid.addItemDecoration(GridSpacingItemDecoration(spanCount, spacing, inCludeEdge))

        viewModel.banners.observe(activity as MainActivity) {

            if(it != null) {
                binding.bannerPager.adapter = BannerPagerAdapter(activity as MainActivity,it)
                binding.totalCount = it.size
                binding.currentPage = 1
            }

        }

        viewModel.goods.observe(activity as MainActivity){
            adapter.replaceList(it.toList())
        }

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.loadHomeData()
            binding.swipeRefresh.isRefreshing = false
        }

        binding.layoutTotal.setOnScrollChangeListener(object : NestedScrollView.OnScrollChangeListener{
            override fun onScrollChange(
                v: NestedScrollView,
                scrollX: Int,
                scrollY: Int,
                oldScrollX: Int,
                oldScrollY: Int
            ) {

                if (v.getChildAt(v.getChildCount() - 1) != null) {
                    if ((scrollY >= (v.getChildAt(v.getChildCount() - 1).getMeasuredHeight() - v.getMeasuredHeight())) &&
                        scrollY > oldScrollY) {
                        viewModel.loadMoreGoodsData()
                    }

                }

            }
        })

        viewModel.loadHomeData()

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