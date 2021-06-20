package com.test.ably.repository

import com.test.ably.model.HomeData

interface HomeRepository {

    fun getHomeData(onSuccess: (homeData : HomeData) -> Unit, onFailure: (t: Throwable) -> Unit)

}