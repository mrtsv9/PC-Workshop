package com.example.pcworkshop.models.pc_accessories.dao

import com.example.pcworkshop.models.pc_accessories.PcAccessories
import com.example.pcworkshop.models.pc_accessories.PostPcAccessories
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PcAccessoriesDao {

    @GET("pc-accessories")
    suspend fun getAllPcAccessories(): Response<List<PcAccessories>>

    @POST("pc-accessories/create")
    fun addPcAccessories(@Body pcAccessories: PostPcAccessories): Call<PostPcAccessories>
}