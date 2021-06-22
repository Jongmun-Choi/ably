package com.test.ably.di.module

import com.test.ably.repository.HomeRepository
import com.test.ably.repository.HomeRepositoryImpl
import com.test.ably.retrofit.AblyApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providesHomeRepository(api : AblyApiService): HomeRepository {
        return HomeRepositoryImpl(api)
    }

}