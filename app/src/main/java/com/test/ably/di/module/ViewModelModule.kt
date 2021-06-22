package com.test.ably.di.module

import com.test.ably.di.factory.MainViewModelFactory
import com.test.ably.repository.HomeRepository
import com.test.ably.retrofit.AblyApiService
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {

    @Provides
    fun providesMainViewModelFactory(homeRepo : HomeRepository) : MainViewModelFactory {
        return MainViewModelFactory(homeRepo)
    }

}