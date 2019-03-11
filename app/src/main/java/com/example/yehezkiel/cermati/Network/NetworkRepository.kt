package com.example.yehezkiel.cermati.Network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Yehezkiel on 1/25/2019.
 */
object NetworkRepository {
    fun create(): RetrofitInstance {
        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.github.com/")
                .build()
        return retrofit.create(RetrofitInstance::class.java)
    }
}