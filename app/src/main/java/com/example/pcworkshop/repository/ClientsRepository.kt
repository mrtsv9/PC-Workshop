package com.example.pcworkshop.repository

import com.example.pcworkshop.dao.ClientsDao
import com.example.pcworkshop.models.clients.Client
import com.example.pcworkshop.services.RetrofitInstance
import retrofit2.Response

class ClientsRepository {

    suspend fun getAllClients(): Response<List<Client>> {
        val retrofitInstance = RetrofitInstance.getRetrofitInstance().create(ClientsDao::class.java)
        return retrofitInstance.getAllClients()
    }

}