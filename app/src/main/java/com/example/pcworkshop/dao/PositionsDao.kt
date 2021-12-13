package com.example.pcworkshop.dao

import com.example.pcworkshop.models.positions.Position
import com.example.pcworkshop.models.positions.PostPosition
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PositionsDao {

    @GET("positions")
    suspend fun getAllPositions(): Response<List<Position>>

    @POST("positions/create")
    fun addPosition(@Body position: PostPosition): Call<PostPosition>
}