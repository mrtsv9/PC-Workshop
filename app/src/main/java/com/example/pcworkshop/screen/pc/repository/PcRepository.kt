package com.example.pcworkshop.screen.pc.repository

import com.example.pcworkshop.models.pc.dao.PcDao
import com.example.pcworkshop.models.pc.Pc
import com.example.pcworkshop.models.pc.PostPc
import com.example.pcworkshop.services.RetrofitInstance
import retrofit2.Call
import retrofit2.Response

class PcRepository {

    suspend fun getAllPc(): Response<List<Pc>> {
        val retrofitInstance = RetrofitInstance.getRetrofitInstance().create(PcDao::class.java)
        return retrofitInstance.getAllPc()
    }

    fun addPc(pc: PostPc): Call<PostPc> {
        val retrofitInstance = RetrofitInstance.getRetrofitInstance().create(PcDao::class.java)
        return retrofitInstance.addPc(pc)
    }

}