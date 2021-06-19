package com.test.ably.di.component

import android.app.Application
import com.test.ably.AblyApplication
import com.test.ably.di.module.ActivityBindingModule
import com.test.ably.di.module.RetrofitModule
import com.test.ably.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,
                    RetrofitModule::class, ActivityBindingModule::class,
                    ViewModelModule::class])

interface AppComponent : AndroidInjector<AblyApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application : Application) : AppComponent
    }

}