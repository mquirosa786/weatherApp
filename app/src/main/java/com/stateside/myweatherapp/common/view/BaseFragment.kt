package com.stateside.myweatherapp.common.view

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import dagger.android.support.AndroidSupportInjection
import io.reactivex.disposables.CompositeDisposable

open class BaseFragment: Fragment() {

    lateinit var currentActivity: BaseActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        this.currentActivity = activity as BaseActivity
    }

    fun showProgress(){
            currentActivity.showProgress()
    }

    fun hideProgress(){
            currentActivity.hideProgress()
    }

    fun getCompositeDisposable():CompositeDisposable{
        return currentActivity.compositeDisposable
    }

    fun goToFragment(f:Fragment, @IdRes idLayout:Int ){
        currentActivity.goToFragment(f as BaseFragment, idLayout)
    }

    fun closeFragment(f: BaseFragment){
        currentActivity.closeFragment(f)
    }

}