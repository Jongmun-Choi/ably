package com.test.ably.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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

    private val _loading = MutableLiveData<LoadingState>()

    val loading: LiveData<LoadingState>
        get() = _loading

    fun loadHomeData() {
        homeRepo.getHomeData(
            onSuccess = { homeData ->
                _loading.value = LoadingState.LOADED
                banners.value = homeData.banners
                goods.value = homeData.goods
            },
            onFailure = { t ->
                t.message?.let { Log.i("test", it)}
            }
        )
    }

    fun loadMoreGoodsData(lastId : String) {

    }
}

@BindingAdapter(value = ["setImageUrl"])
fun ImageView.bindImageUrl(url: String) {
    Log.i("img_test", "url = ${url}")
    GlideApp.with(this).load(url).into(this)
}
