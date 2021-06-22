package com.test.ably.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.rsupportloadimage.model.LoadingState
import com.test.ably.model.Banner
import com.test.ably.model.Goods
import com.test.ably.model.HomeData
import com.test.ably.repository.HomeRepository
import com.test.ably.retrofit.AblyApiService
import com.test.ably.util.GlideApp
import kotlinx.coroutines.Dispatchers
import okhttp3.Callback
import okhttp3.Dispatcher
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject
import kotlin.coroutines.coroutineContext
import kotlin.coroutines.suspendCoroutine

class MainViewModel(private val homeRepo : HomeRepository) : ViewModel() {

    val banners : MutableLiveData<List<Banner>> = MutableLiveData()
    val goods : MutableLiveData<List<Goods>> = MutableLiveData()

    val goodsList = mutableListOf<Goods> ()
    val bannerList = mutableListOf<Banner>()

    private val _loading = MutableLiveData<LoadingState>()

    val loading: LiveData<LoadingState>
        get() = _loading

    fun loadHomeData() {
        homeRepo.getHomeData(
            onSuccess = { homeData ->
                _loading.value = LoadingState.LOADED
                goodsList.clear()
                bannerList.clear()

                goodsList.addAll(homeData.goods)
                bannerList.addAll(homeData.banners)

                banners.value = bannerList
                goods.value = goodsList
            },
            onFailure = { t ->
                t.message?.let { Log.i("test", it)}
            }
        )
    }

    fun loadMoreGoodsData() {
        if(goods.value != null) {
            homeRepo.getGoodsMore(goods.value!!.get(goods.value!!.lastIndex).id.toString(),
            onSuccess = {
                response_goods -> goodsList.addAll(response_goods)
                goods.value = goodsList
            },
            onFailure = {
                it.message?.let { Log.i("test", it)}
            })
        }
    }

    fun setLikeData(id : Int) {
        val currentGoods = goodsList.find { it.id == id }
        val newGoods = currentGoods.let {Goods(it!!.id,it.name, it.image, it.actual_price, it.price, it.is_new, it.sell_count, !it.like)}
        goodsList.set(goodsList.indexOf(currentGoods), newGoods)
        goods.value = goodsList
    }

}

@BindingAdapter(value = ["setRoundImageUrl"])
fun ImageView.bindRoundImageUrl(url: String) {
    Log.i("img_test", "url = ${url}")
    val options = RequestOptions.bitmapTransform(RoundedCorners(10))
    GlideApp.with(this).load(url).apply(options).into(this)
}

@BindingAdapter(value = ["setImageUrl"])
fun ImageView.bindImageUrl(url: String) {
    Log.i("img_test", "url = ${url}")
    GlideApp.with(this).load(url).into(this)
}

@BindingAdapter(value = ["calcPercent"])
fun TextView.bindPercent(goods : Goods) {
    val calc_percent = ((goods.actual_price - goods.price).toFloat() / goods.actual_price.toFloat()) * 100
    this.text = "${Math.round(calc_percent)}%"
}