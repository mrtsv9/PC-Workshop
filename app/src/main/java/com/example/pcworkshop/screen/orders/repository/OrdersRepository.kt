package com.example.pcworkshop.screen.orders.repository

import com.example.pcworkshop.dao.OrdersDao
import com.example.pcworkshop.models.orders.Orders
import com.example.pcworkshop.services.RetrofitInstance
import retrofit2.Response

class OrdersRepository {

    suspend fun getAllOrders(): Response<List<Orders>> {
        val retrofitInstance = RetrofitInstance.getRetrofitInstance().create(OrdersDao::class.java)
        return retrofitInstance.getAllOrders()
    }

//    suspend fun getClient(clientId: Int): Response<Client> {
//        val retrofitInstance = RetrofitInstance.getRetrofitInstance().create(ClientsDao::class.java)
//        return retrofitInstance.getClient(clientId)
//    }

}