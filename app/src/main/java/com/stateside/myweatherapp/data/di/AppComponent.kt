package com.stateside.myweatherapp.data.di

import android.app.Application
import com.stateside.myweatherapp.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AndroidInjectionModule::class,
        AppModule::class,

        ActivityBuildersModule::class,
        FragmentBuildersModule::class,

        NetworkModule::class,
        FragmentModule::class

    ]
)
interface AppComponent {

    fun inject(application: BaseApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun viewModule(appModule: AppModule): Builder

        fun networkModule(networkModule: NetworkModule): Builder

        fun build(): AppComponent
    }

}