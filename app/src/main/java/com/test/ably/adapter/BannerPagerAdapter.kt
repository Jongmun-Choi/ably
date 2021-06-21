package com.test.ably.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.test.ably.R
import com.test.ably.databinding.ItemBannerBinding
import com.test.ably.databinding.ItemBannerBindingImpl
import com.test.ably.model.Banner
import com.test.ably.util.GlideApp

class BannerPagerAdapter(private val mContext : Context, val bannerList : List<Banner>) : PagerAdapter() {

    override fun getCount(): Int {
        return bannerList.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var inflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var binding = DataBindingUtil.inflate<ItemBannerBinding>(inflater, R.layout.item_banner, container, false)
        binding.banner = bannerList.get(position)
        val vp = container as ViewPager
        vp.addView(binding.root, 0)
        return binding.root
    }

    override fun isViewFromObject(view: View, item: Any): Boolean {
        return view === item
    }

    override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
        container.removeView(view as android.view.View?)
    }
}