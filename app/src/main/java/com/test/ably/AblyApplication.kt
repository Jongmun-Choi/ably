package com.test.ably


import com.test.ably.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class AblyApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this@AblyApplication)
    }

    override fun onCreate() {
        super.onCreate()
    }
}