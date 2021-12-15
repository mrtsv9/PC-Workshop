package com.example.pcworkshop.models.clients.dao

import com.example.pcworkshop.models.clients.Clients
import com.example.pcworkshop.models.clients.PostClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ClientsDao {

    @GET("clients")
    suspend fun getAllClients(): Response<List<Clients>>

    @GET("clients/{id}")
    suspend fun getClient(@Path("id") id: Int): Response<Clients>

    @POST("clients/create")
    fun addClient(@Body client: PostClient): Call<PostClient>

    @FormUrlEncoded
    @PUT("clients/update/{client_id}")
    fun updateClient(
        @Path("client_id") id: Int,
        @Field("first_name") firstName: String,
        @Field("last_name") lastName: String,
        @Field("email") email: String,
        @Field("phone_number") phoneNumber: String,
        @Field("password") password: String
    ): Call<PostClient>


    @DELETE("clients/delete/{id}")
    fun deleteClient(@Path("id") id: Int): Call<Unit>

}