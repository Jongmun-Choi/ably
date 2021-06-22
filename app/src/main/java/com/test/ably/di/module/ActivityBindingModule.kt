package com.test.ably.di.module

import com.test.ably.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {


    @ContributesAndroidInjector
    abstract fun mainActivity() : MainActivity

}