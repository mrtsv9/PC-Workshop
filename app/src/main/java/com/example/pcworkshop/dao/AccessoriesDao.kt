package com.example.pcworkshop.dao

import com.example.pcworkshop.models.accessories.Accessories
import retrofit2.Response
import retrofit2.http.GET

interface AccessoriesDao {

    @GET("accessories")
    suspend fun getAllAccessories(): Response<List<Accessories>>

}