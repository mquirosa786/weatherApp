package com.stateside.myweatherapp.data.net.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GetWeatherResponse {

    @Expose @SerializedName("latitude") var latitude:Double? = null
    @Expose @SerializedName("longitude") var longitude:Double? = null
    @Expose @SerializedName("timezone") var timezone:String? = null
    @Expose @SerializedName("offset") var offset:Double? = null
    @Expose @SerializedName("currently") var currently:CurrentlyData? = null

    class CurrentlyData{
        @Expose @SerializedName("time") var time:String= ""
        @Expose @SerializedName("summary") var summary:String? = ""
        @Expose @SerializedName("icon") var icon:String? = ""
        @Expose @SerializedName("nearestStormDistance") var nearestStormDistance:Float? = 0.00f
        @Expose @SerializedName("nearestStormBearing") var nearestStormBearing:Float? = 0.00f
        @Expose @SerializedName("precipIntensity") var precipIntensity:Float? = 0.00f
        @Expose @SerializedName("precipProbability") var precipProbability:Float? = 0.00f
        @Expose @SerializedName("temperature") var temperature:Float? = 0.00f
        @Expose @SerializedName("apparentTemperature") var apparentTemperature:Float? = 0.00f
        @Expose @SerializedName("dewPoint") var dewPoint:Float? = 0.00f
        @Expose @SerializedName("humidity") var humidity:Float? = 0.00f
        @Expose @SerializedName("pressure") var pressure:Float? = 0.00f
        @Expose @SerializedName("windSpeed") var windSpeed:Float? = 0.00f
        @Expose @SerializedName("windGust") var windGust:Float? = 0.00f
        @Expose @SerializedName("windBearing") var windBearing:Float? = 0.00f
        @Expose @SerializedName("cloudCover") var cloudCover:Float? = 0.00f
        @Expose @SerializedName("uvIndex") var uvIndex:Float? = 0.00f
        @Expose @SerializedName("visibility") var visibility:Float? = 0.00f
        @Expose @SerializedName("ozone") var ozone:Float? = 0.00f


    }
}