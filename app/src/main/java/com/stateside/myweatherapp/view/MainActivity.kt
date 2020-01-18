package com.stateside.myweatherapp.view

import android.content.Context
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.stateside.myweatherapp.R
import com.stateside.myweatherapp.common.view.BaseActivity
import com.stateside.myweatherapp.data.net.response.GetWeatherResponse
import com.stateside.myweatherapp.databinding.ActivityMainBinding
import com.stateside.myweatherapp.viewmodel.WeatherViewModel
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.*
import javax.inject.Inject


class MainActivity : BaseActivity() {

    @Inject
    lateinit var weatherViewModel: WeatherViewModel
    @Inject
    lateinit var appContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.setWeatherViewModel(weatherViewModel)
        weatherViewModel.weatherResponse.set(null);
        val placesClient = Places.createClient(this.appContext)
        val autocompleteFragment =
            supportFragmentManager.findFragmentById(R.id.autocomplete_fragment) as AutocompleteSupportFragment?

        autocompleteFragment!!.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME,Place.Field.LAT_LNG))

        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
                // TODO: Get info about the selected place.
                getWeather(place.latLng!!.latitude,place.latLng!!.longitude)
            }

            override fun onError(status: Status) {
                // TODO: Handle the error.
                Timber.i("An error occurred: $status")
            }
        })

    }

    fun getWeather(latitude: Double, longitude: Double){
        weatherViewModel.getUserByIdSingle(latitude,longitude)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<GetWeatherResponse> {
                override fun onSuccess(response: GetWeatherResponse) {
                    hideProgress()
                    weatherViewModel.weatherResponse.set(response)
                }

                override fun onSubscribe(d: Disposable) {
                    Timber.i("Subscripcion SyncEsctrucutrasAfiliaciones")
                    showProgress()
                }

                override fun onError(e: Throwable) {
                    Timber.e(e)
                    hideProgress()
                }

            })
    }
}
