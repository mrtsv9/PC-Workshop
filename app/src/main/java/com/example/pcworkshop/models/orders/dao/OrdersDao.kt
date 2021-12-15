package com.example.pcworkshop.models.orders.dao

import com.example.pcworkshop.models.orders.Orders
import com.example.pcworkshop.models.orders.PostOrder
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface OrdersDao {

    @GET("orders")
    suspend fun getAllOrders(): Response<List<Orders>>

    @POST("orders/create")
    fun addOrder(@Body order: PostOrder): Call<PostOrder>

    @FormUrlEncoded
    @PUT("orders/update/{order_id}")
    fun updateOrder(
        @Path("order_id") orderId: Int,
        @Field("address") address: String,
        @Field("client_id") clientId: Int,
        @Field("payment_method_id") paymentMethodId: Int,
        @Field("delivery_method_id") deliveryMethodId: Int
    ): Call<PostOrder>

    @DELETE("orders/delete/{order_id}")
    fun deleteOrder(@Path("order_id") orderId: Int): Call<Unit>
}