package com.udacity.asteroidradar.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.udacity.asteroidradar.Constants.BASE_URL
import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .addConverterFactory(ScalarsConverterFactory.create())
    .build()

interface NasaApiService {

    @GET("neo/rest/v1/feed")
    fun getAsteroids(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("api_key") apiKey: String
    ): Deferred<String>
}

object NasaApi {
    val retrofitService : NasaApiService by lazy { retrofit.create(NasaApiService::class.java) }
}
