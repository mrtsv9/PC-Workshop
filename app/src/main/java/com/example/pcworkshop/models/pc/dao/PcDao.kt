package com.example.pcworkshop.models.pc.dao

import com.example.pcworkshop.models.pc.Pc
import com.example.pcworkshop.models.pc.PostPc
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PcDao{

    @GET("pc")
    suspend fun getAllPc(): Response<List<Pc>>

    @POST("pc/create")
    fun addPc(@Body pc: PostPc): Call<PostPc>

}