package com.example.pcworkshop.dao

import com.example.pcworkshop.models.types_of_accessories.TypesOfAccessories
import retrofit2.Response
import retrofit2.http.GET

interface TypesOfAccessoriesDao {

    @GET("types-of-accessories")
    suspend fun getAllTypesOfAccessories(): Response<List<TypesOfAccessories>>

}