package com.stateside.myweatherapp

import android.app.Application
import com.stateside.myweatherapp.common.view.BaseFragment
import com.stateside.myweatherapp.data.di.AppComponent
import com.stateside.myweatherapp.data.di.DaggerAppComponent
import com.stateside.myweatherapp.data.di.NetworkModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.reactivex.plugins.RxJavaPlugins
import timber.log.Timber
import javax.inject.Inject
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.PlacesClient
import android.R.attr.apiKey
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



/**
 * MQ
 */
class BaseApplication : Application(), HasAndroidInjector{
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }

    companion object {
        var currentURL: String? = null
        var currentFragment: BaseFragment? = null

    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        RxJavaPlugins.setErrorHandler {
            Timber.e("mquirosa %sss", it.message)
        }
        Places.initialize(applicationContext, "AIzaSyBQgL3J4SYRGb3-5ogEJjJjONC9BylAkuM")


        Timber.plant(Timber.DebugTree())

        appComponent = DaggerAppComponent.builder()
            .application(this)
            .networkModule(NetworkModule(this))
            .build()
        appComponent.inject(this)




    }


}