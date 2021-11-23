package com.example.pcworkshop.services

import com.example.pcworkshop.clients.Client
import retrofit2.Call
import retrofit2.http.GET

interface MyAPI {

    @GET("clients")
    fun getAllClients(): Call<List<Client>>

}