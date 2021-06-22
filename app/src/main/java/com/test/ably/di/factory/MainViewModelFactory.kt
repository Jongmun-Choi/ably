package com.test.ably.di.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.ably.repository.HomeRepository
import com.test.ably.retrofit.AblyApiService
import com.test.ably.viewmodel.MainViewModel
import java.lang.IllegalArgumentException
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory @Inject constructor(private val homeRepo : HomeRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(homeRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}