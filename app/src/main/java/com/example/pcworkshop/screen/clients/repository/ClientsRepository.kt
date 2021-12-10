package com.example.pcworkshop.screen.clients.repository

import com.example.pcworkshop.dao.ClientsDao
import com.example.pcworkshop.models.clients.Clients
import com.example.pcworkshop.services.RetrofitInstance
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

}