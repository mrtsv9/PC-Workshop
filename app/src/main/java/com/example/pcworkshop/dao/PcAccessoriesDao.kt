package com.example.pcworkshop.dao

import com.example.pcworkshop.models.pc_accessories.PcAccessories
import retrofit2.Response
import retrofit2.http.GET

interface PcAccessoriesDao {

    @GET("pc-accessories")
    suspend fun getAllPcAccessories(): Response<List<PcAccessories>>

}