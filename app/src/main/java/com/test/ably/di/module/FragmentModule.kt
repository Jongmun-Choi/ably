package com.test.ably.di.module

import com.test.ably.fragment.HomeFragment
import com.test.ably.fragment.LikeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun homeFragment() : HomeFragment

    @ContributesAndroidInjector
    abstract fun likeFragment() : LikeFragment

}