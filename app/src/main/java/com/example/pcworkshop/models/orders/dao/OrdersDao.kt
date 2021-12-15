package com.example.pcworkshop.models.orders.dao

import com.example.pcworkshop.models.orders.Orders
import com.example.pcworkshop.models.orders.PostOrder
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface OrdersDao {

    @GET("orders")
    suspend fun getAllOrders(): Response<List<Orders>>

    @POST("orders/create")
    fun addOrder(@Body order: PostOrder): Call<PostOrder>
}