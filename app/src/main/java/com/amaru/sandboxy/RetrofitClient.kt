package com.amaru.sandboxy

import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://100.90.172.241:3000/"

    val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create())
            .build()
    }
    val fetchInterface: FetchInterface by lazy {
        instance.create(FetchInterface::class.java)
    }
}
