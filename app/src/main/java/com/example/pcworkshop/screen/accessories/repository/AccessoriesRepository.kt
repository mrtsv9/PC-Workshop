package com.example.pcworkshop.screen.accessories.repository

import com.example.pcworkshop.dao.AccessoriesDao
import com.example.pcworkshop.models.accessories.Accessories
import com.example.pcworkshop.services.RetrofitInstance
import retrofit2.Response

class AccessoriesRepository {

    suspend fun getAllAccessories(): Response<List<Accessories>> {
        val retrofitInstance = RetrofitInstance.getRetrofitInstance().create(AccessoriesDao::class.java)
        return retrofitInstance.getAllAccessories()
    }


}