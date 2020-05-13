package com.tjclawson.spacex.service

import com.tjclawson.spacex.model.Launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface SpaceXApiService {

    companion object {
        const val BASE_URL = "https://api.spacexdata.com/v3/"
        private var instance: SpaceXApiService? = null

        // Returns instance if it has been instantiated, otherwise instantiates and returns
        // ensures singleton pattern for retrofit instance
        operator fun invoke(): SpaceXApiService? {
            if (instance == null) {
                instance = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(SpaceXApiService::class.java)
            }

            return instance
        }
    }

    @GET("launches/past")
    suspend fun getPastLaunches(): MutableList<Launch>
}