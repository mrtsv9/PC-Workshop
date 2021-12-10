package com.example.pcworkshop.dao

import com.example.pcworkshop.models.orders.Orders
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface OrdersDao {

    @GET("orders")
    suspend fun getAllOrders(): Response<List<Orders>>

}