package com.example.pcworkshop.dao

import com.example.pcworkshop.models.clients.Clients
import com.example.pcworkshop.models.clients.PostClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ClientsDao {

    @GET("clients")
    suspend fun getAllClients(): Response<List<Clients>>

    @GET("clients/{id}")
    suspend fun getClient(@Path("id") id: Int): Response<Clients>

    @POST("clients/create")
    fun addClient(@Body client: PostClient): Call<PostClient>

}