package com.khanhnq.data.source.remote

import com.khanhnq.data.entity.LaunchEntity
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("launches")
    suspend fun getAllLaunches(): Response<List<LaunchEntity>>

    @GET("launches/{id}")
    suspend fun getLaunch(@Path("id") id: String): Response<LaunchEntity>

    companion object {
        private const val BASE_URL = "https://api.spacexdata.com/v4/"

        fun makeService(): ApiService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

