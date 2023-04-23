package com.example.alfa_bank_android_app_teacher.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiFactory {

    companion object {
        const val BASE_BD_URL2 = "https://d5dr8ccmms8urkspiqc2.apigw.yandexcloud.net"
        const val BASE_BD_URL = "https://d5dq8j6lmvud3ftlag47.apigw.yandexcloud.net"

        fun getBdApiService(): BdApiService = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_BD_URL2)
            .client(
                OkHttpClient.Builder().addInterceptor(
                    HttpLoggingInterceptor().apply
                    {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    }
                ).build()
            )
            .build()
            .create(BdApiService::class.java)

        fun getApiService(url:String= BASE_BD_URL): ApiService = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(url)
            .client(
                OkHttpClient.Builder().addInterceptor(
                    HttpLoggingInterceptor().apply
                    {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    }
                ).build()
            )
            .build()
            .create(ApiService::class.java)
    }
}