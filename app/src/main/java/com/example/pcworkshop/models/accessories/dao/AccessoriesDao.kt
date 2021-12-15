package com.example.pcworkshop.models.accessories.dao

import com.example.pcworkshop.models.accessories.Accessories
import com.example.pcworkshop.models.accessories.PostAccessory
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AccessoriesDao {

    @GET("accessories")
    suspend fun getAllAccessories(): Response<List<Accessories>>

    @POST("accessories/create")
    fun addAccessory(@Body accessory: PostAccessory): Call<PostAccessory>
}