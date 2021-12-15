package com.example.pcworkshop.screen.pc_accessories.repository

import com.example.pcworkshop.models.pc_accessories.dao.PcAccessoriesDao
import com.example.pcworkshop.models.pc_accessories.PcAccessories
import com.example.pcworkshop.models.pc_accessories.PostPcAccessories
import com.example.pcworkshop.services.RetrofitInstance
import retrofit2.Call
import retrofit2.Response

class PcAccessoriesRepository {

    suspend fun getAllPcAccessories(): Response<List<PcAccessories>> {
        val retrofitInstance = RetrofitInstance.getRetrofitInstance().create(PcAccessoriesDao::class.java)
        return retrofitInstance.getAllPcAccessories()
    }

    fun addPcAccessories(pcAccessories: PostPcAccessories): Call<PostPcAccessories> {
        val retrofitInstance = RetrofitInstance.getRetrofitInstance().create(PcAccessoriesDao::class.java)
        return retrofitInstance.addPcAccessories(pcAccessories)
    }

}