package com.example.recyclerviewmultipletype

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
    var loggingInterceptor = HttpLoggingInterceptor()
    val interceptor = Interceptor {
        var request = it.request()
        request = request.newBuilder().header("Token","OsamaOsamaOsamaOsamaOsamaOsama").build()
        it.proceed(request)
    }
    var client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .addInterceptor(loggingInterceptor)
    val api:PostsAPI by lazy{
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostsAPI::class.java)
    }
}