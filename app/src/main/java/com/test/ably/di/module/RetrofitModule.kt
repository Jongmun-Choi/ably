package com.test.ably.di.module

import com.test.ably.retrofit.AblyApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetrofitModule {

    @Provides
    @Singleton
    fun provideAblyApi(okHttpClient: OkHttpClient, factory : Converter.Factory) : AblyApiService {
        return Retrofit.Builder()
            .baseUrl("http://d2bab9i9pr8lds.cloudfront.net/api/")
            .addConverterFactory(factory)
            .client(okHttpClient)
            .build()
            .create(AblyApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor) : OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor).build();
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor() : HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideConverterFactory() : Converter.Factory {
        return GsonConverterFactory.create()
    }
}