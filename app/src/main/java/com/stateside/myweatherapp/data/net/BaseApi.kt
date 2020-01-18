package com.stateside.myweatherapp.data.net

import com.stateside.myweatherapp.data.net.response.GetWeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BaseApi {
    @GET("/forecast/{secretKey}/{latitude},{longitude}")
    fun getWeather(@Path("secretKey") secretKey:String, @Path("latitude")
    latitude: Double, @Path("longitude") longitude: Double, @Query("exclude") exclude:ArrayList<String>): Single<GetWeatherResponse>
}
