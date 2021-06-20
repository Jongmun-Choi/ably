package com.test.ably.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.test.ably.model.HomeData
import com.test.ably.repository.HomeRepository
import com.test.ably.retrofit.AblyApiService
import okhttp3.Callback
import okhttp3.Dispatcher
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject
import kotlin.coroutines.coroutineContext
import kotlin.coroutines.suspendCoroutine

class MainViewModel(private val homeRepo : HomeRepository) : ViewModel() {

    fun loadHomeData() {
        homeRepo.getHomeData(
            onSuccess = { homeData -> },
            onFailure = { t -> t.message?.let { Log.i("test", it)} }
        )
    }

    fun loadMoreGoodsData(lastId : String) {

    }
}