package com.example.pcworkshop.dao

import com.example.pcworkshop.models.accessories.TypeOfAccessories
import com.example.pcworkshop.models.types_of_accessories.PostType
import com.example.pcworkshop.models.types_of_accessories.TypesOfAccessories
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TypesOfAccessoriesDao {

    @GET("types-of-accessories")
    suspend fun getAllTypesOfAccessories(): Response<List<TypesOfAccessories>>

    @POST("types-of-accessories/create")
    fun addType(@Body type: PostType): Call<PostType>
}