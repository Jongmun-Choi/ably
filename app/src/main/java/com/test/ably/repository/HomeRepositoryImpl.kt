package com.test.ably.repository

import android.util.Log
import com.test.ably.model.Goods
import com.test.ably.model.HomeData
import com.test.ably.retrofit.AblyApiService
import retrofit2.Call
import retrofit2.Response

class HomeRepositoryImpl(private val retrofit : AblyApiService) : HomeRepository {

    override fun getHomeData(
        onSuccess: (homeData: HomeData) -> Unit,
        onFailure: (t: Throwable) -> Unit
    ) {
        retrofit.getHomeItemList().enqueue(object : retrofit2.Callback<HomeData> {
            override fun onResponse(call: Call<HomeData>, response: Response<HomeData>) {
                if(response.body() != null) {
                    onSuccess.invoke(response.body()!!)
                }else {
                    onFailure.invoke(Throwable("data is empty"))
                }
            }

            override fun onFailure(call: Call<HomeData>, t: Throwable) {
                onFailure.invoke(t);
            }
        })
    }

    override fun getGoodsMore(
        lastId: String,
        onSuccess: (goodsList: List<Goods>) -> Unit,
        onFailure: (t: Throwable) -> Unit
    ) {
        retrofit.getGoods(lastId).enqueue(object : retrofit2.Callback<HomeData> {
            override fun onResponse(call: Call<HomeData>, response: Response<HomeData>) {
                if(response.body() != null) {
                    onSuccess.invoke(response.body()!!.goods)
                }else {
                    onFailure.invoke(Throwable("data is empty"))
                }
            }

            override fun onFailure(call: Call<HomeData>, t: Throwable) {
                onFailure.invoke(t)
            }
        })
    }
}