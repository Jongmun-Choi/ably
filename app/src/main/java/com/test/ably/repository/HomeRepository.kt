package com.test.ably.repository

import com.test.ably.model.Goods
import com.test.ably.model.HomeData

interface HomeRepository {

    fun getHomeData(onSuccess: (homeData : HomeData) -> Unit, onFailure: (t: Throwable) -> Unit)
    fun getGoodsMore(lastId : String , onSuccess: (goodsList: List<Goods>) -> Unit, onFailure: (t: Throwable) -> Unit)
}