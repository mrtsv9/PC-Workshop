package com.example.pcworkshop.screen.clients.repository

import com.example.pcworkshop.models.clients.dao.ClientsDao
import com.example.pcworkshop.models.clients.Clients
import com.example.pcworkshop.models.clients.PostClient
import com.example.pcworkshop.services.RetrofitInstance
import retrofit2.Call
import retrofit2.Response

class ClientsRepository {

    suspend fun getAllClients(): Response<List<Clients>> {
        val retrofitInstance = RetrofitInstance.getRetrofitInstance().create(ClientsDao::class.java)
        return retrofitInstance.getAllClients()
    }

    suspend fun getClient(clientId: Int): Response<Clients> {
        val retrofitInstance = RetrofitInstance.getRetrofitInstance().create(ClientsDao::class.java)
        return retrofitInstance.getClient(clientId)
    }

    fun addClient(client: PostClient): Call<PostClient> {
        val retrofitInstance = RetrofitInstance.getRetrofitInstance().create(ClientsDao::class.java)
        return retrofitInstance.addClient(client)
    }

}