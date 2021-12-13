package com.example.pcworkshop.screen.types_of_accessories.repository

import com.example.pcworkshop.dao.PositionsDao
import com.example.pcworkshop.dao.TypesOfAccessoriesDao
import com.example.pcworkshop.models.positions.PostPosition
import com.example.pcworkshop.models.types_of_accessories.TypesOfAccessories
import com.example.pcworkshop.services.RetrofitInstance
import retrofit2.Call
import retrofit2.Response

class TypesOfAccessoriesRepository {

    suspend fun getAllTypesOfAccessories(): Response<List<TypesOfAccessories>> {
        val retrofitInstance = RetrofitInstance.getRetrofitInstance().create(TypesOfAccessoriesDao::class.java)
        return retrofitInstance.getAllTypesOfAccessories()
    }

    fun addPosition(position: PostPosition): Call<PostPosition> {
        val retrofitInstance = RetrofitInstance.getRetrofitInstance().create(PositionsDao::class.java)
        return  retrofitInstance.addPosition(position)
    }

}