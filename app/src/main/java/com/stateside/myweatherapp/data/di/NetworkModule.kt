package com.stateside.myweatherapp.data.di

import com.google.common.collect.ImmutableList
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.stateside.myweatherapp.BaseApplication
import com.stateside.myweatherapp.data.net.BaseApi
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

const val KEY_CURRENT_BASE_URL: String = "CURRENT_URL"

/*@Module(includes = [BaseApiModule::class])*/
@Module
class NetworkModule(var baseApplication: BaseApplication) {

    @Provides
    @Named(KEY_CURRENT_BASE_URL)
    fun provideBaseUrl() = "https://api.darksky.net"

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val loggin = HttpLoggingInterceptor()
        loggin.level = HttpLoggingInterceptor.Level.BODY
        return loggin
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor) = OkHttpClient.Builder()
        .connectionSpecs(ImmutableList.of(ConnectionSpec.MODERN_TLS, ConnectionSpec.CLEARTEXT))
        .addInterceptor(loggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun provideGson() = GsonBuilder()
        .excludeFieldsWithoutExposeAnnotation()
        .create()

    @Provides
    @Singleton
    internal fun provideRxJavaCallAdapterFactory() = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())

    @Provides
    @Singleton
    fun provideBaseApi(okHttpClient: OkHttpClient,
                       @Named(KEY_CURRENT_BASE_URL)baseURL:String,
                       rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
                       gson: Gson
        ) = Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .client(okHttpClient)
                .build().create(BaseApi::class.java)

}
