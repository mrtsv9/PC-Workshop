package com.example.pcworkshop.screen.pc_accessories.repository

import com.example.pcworkshop.dao.OrdersDao
import com.example.pcworkshop.dao.PcAccessoriesDao
import com.example.pcworkshop.models.orders.Orders
import com.example.pcworkshop.models.pc_accessories.PcAccessories
import com.example.pcworkshop.services.RetrofitInstance
import retrofit2.Response

class PcAccessoriesRepository {

    suspend fun getAllPcAccessories(): Response<List<PcAccessories>> {
        val retrofitInstance = RetrofitInstance.getRetrofitInstance().create(PcAccessoriesDao::class.java)
        return retrofitInstance.getAllPcAccessories()
    }

}