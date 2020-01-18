package com.stateside.myweatherapp.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.stateside.myweatherapp.data.net.response.GetWeatherResponse
import com.stateside.myweatherapp.repository.WeatherRepository
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
public class WeatherViewModel
    @Inject constructor(private var weatherRepository: WeatherRepository) :ViewModel() {
    val weatherResponse: ObservableField<GetWeatherResponse> = ObservableField()

    fun getUserByIdSingle(latitude: Double, longitude: Double):Single<GetWeatherResponse>{
        return weatherRepository.getWeather(latitude,longitude)
    }
}