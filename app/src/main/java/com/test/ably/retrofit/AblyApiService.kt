package com.test.ably.retrofit

import com.test.ably.model.Goods
import com.test.ably.model.HomeData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AblyApiService {

    @GET("home")
    fun getHomeItemList() : Call<HomeData>

    @GET("home/goods")
    fun getGoods(@Query("lastId") lastId : String): Call<HomeData>
}