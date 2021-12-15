package com.example.pcworkshop.models.employees.dao

import com.example.pcworkshop.models.employees.Employees
import com.example.pcworkshop.models.employees.PostEmployee
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EmployeesDao {

    @GET("employees")
    suspend fun getAllEmployees(): Response<List<Employees>>

    @GET("employees/{id}")
    suspend fun getClient(@Path("id") id: Int): Response<Employees>

    @POST("employees/create")
    fun addEmployee(@Body employee: PostEmployee): Call<PostEmployee>
}