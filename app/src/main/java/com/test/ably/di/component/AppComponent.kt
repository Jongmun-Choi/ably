package com.test.ably.di.component

import android.app.Application
import com.test.ably.AblyApplication
import com.test.ably.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,
                    RetrofitModule::class, ActivityBindingModule::class,
                    ViewModelModule::class, RepositoryModule::class,
                    FragmentModule::class])

interface AppComponent : AndroidInjector<AblyApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application : Application) : AppComponent
    }

}