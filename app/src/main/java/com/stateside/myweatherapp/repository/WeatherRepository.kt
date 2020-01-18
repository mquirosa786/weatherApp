package com.stateside.myweatherapp.repository

import com.stateside.myweatherapp.data.net.BaseApi
import com.stateside.myweatherapp.data.net.response.GetWeatherResponse
import io.reactivex.Single
import javax.inject.Inject

class WeatherRepository
@Inject constructor(var baseApi: BaseApi){

    fun getWeather(latitude: Double, longitude: Double):Single<GetWeatherResponse>{
        val arrayList = ArrayList<String>()
        arrayList.add("minutely")
        arrayList.add("hourly")
        arrayList.add("daily")
        arrayList.add("alerts")
        arrayList.add("flags")
        return baseApi.getWeather("19912bd4e1e1cac60b174cfc8270749f",latitude,longitude, arrayList)
    }

}