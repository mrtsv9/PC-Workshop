package com.example.pcworkshop.screen.positions.repository

import com.example.pcworkshop.dao.PcDao
import com.example.pcworkshop.dao.PositionsDao
import com.example.pcworkshop.models.pc.Pc
import com.example.pcworkshop.models.positions.Position
import com.example.pcworkshop.models.positions.PostPosition
import com.example.pcworkshop.services.RetrofitInstance
import retrofit2.Call
import retrofit2.Response

class PositionsRepository {

    suspend fun getAllPositions(): Response<List<Position>> {
        val retrofitInstance = RetrofitInstance.getRetrofitInstance().create(PositionsDao::class.java)
        return retrofitInstance.getAllPositions()
    }

    fun addPosition(position: PostPosition): Call<PostPosition> {
        val retrofitInstance = RetrofitInstance.getRetrofitInstance().create(PositionsDao::class.java)
        return  retrofitInstance.addPosition(position)
    }

}