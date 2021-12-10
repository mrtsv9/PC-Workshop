//package com.example.pcworkshop.z_depcrecated
//
//import com.example.pcworkshop.models.clients.Client
//import okhttp3.ResponseBody
//import retrofit2.Call
//import retrofit2.Response
//import retrofit2.http.Body
//import retrofit2.http.GET
//import retrofit2.http.POST
//
//interface MyAPI {
//
//    @GET("clients")
//    suspend fun getAllClients(): Response<List<Client>>
//
//    @POST("clients/create")
//    fun addClient(@Body client: Client): Call<Client>
//
//}