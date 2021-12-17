package com.example.pcworkshop.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object {
        private const val URL = "http://10.0.2.2:3000/"
//        private const val URL = "http://192.168.0.13:3000/"

        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}