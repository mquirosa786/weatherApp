package com.stateside.myweatherapp.data.di

import com.stateside.myweatherapp.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract fun bindLoginActivity(): MainActivity
}
