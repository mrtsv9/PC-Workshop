package com.example.pcworkshop.dao

import com.example.pcworkshop.models.clients.Client
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ClientsDao {

    @GET("clients")
    suspend fun getAllClients(): Response<List<Client>>

    @GET("clients/{id}")
    fun getClient(@Path("id") id: Int): Call<Client>

    @POST("clients/create")
    fun addClient(@Body client: Client): Call<Client>

}