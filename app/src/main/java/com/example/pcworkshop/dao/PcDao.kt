package com.example.pcworkshop.dao

import com.example.pcworkshop.models.pc.Pc
import retrofit2.Response
import retrofit2.http.GET

interface PcDao{

    @GET("pc")
    suspend fun getAllPc(): Response<List<Pc>>

}