package com.example.pcworkshop.dao

import com.example.pcworkshop.models.clients.Client
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ClientsDao {

    @GET("clients")
    fun getAllClients(): Call<List<Client>>

    @POST("clients/create")
    fun addClient(@Body client: Client): Call<Client>


}