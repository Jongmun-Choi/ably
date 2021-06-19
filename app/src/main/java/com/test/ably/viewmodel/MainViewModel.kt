package com.test.ably.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.test.ably.model.HomeData
import com.test.ably.retrofit.AblyApiService
import okhttp3.Callback
import okhttp3.Dispatcher
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject
import kotlin.coroutines.coroutineContext
import kotlin.coroutines.suspendCoroutine

class MainViewModel(private val retrofit: AblyApiService) : ViewModel() {

    fun loadHomeData() {
        retrofit.getHomeItemList().enqueue(object : retrofit2.Callback<HomeData> {
            override fun onResponse(call: Call<HomeData>, response: Response<HomeData>) {
                if(response.body() != null) {
                    for (banner in response.body()!!.banners) {
                        Log.i("test", "name = ${banner.image}")
                    }

                    for (goods in response.body()!!.goods) {
                        Log.i("test", "name = ${goods.image}")
                    }
                }
            }

            override fun onFailure(call: Call<HomeData>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun loadMoreGoodsData(lastId : String) {

    }
}