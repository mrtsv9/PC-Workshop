package com.example.pcworkshop.services

import com.example.pcworkshop.clients.Client
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MyAPI {

    @GET("clients")
    fun getAllClients(): Call<List<Client>>

    @POST("clients/create")
    fun addClient(@Body client: Client): Call<Client>

}