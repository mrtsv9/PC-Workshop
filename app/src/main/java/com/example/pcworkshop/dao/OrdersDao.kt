package com.example.pcworkshop.dao

import com.example.pcworkshop.models.orders.Orders
import retrofit2.Call
import retrofit2.http.GET

interface OrdersDao {

    @GET("orders")
    fun getAllOrders(): Call<List<Orders>>

}