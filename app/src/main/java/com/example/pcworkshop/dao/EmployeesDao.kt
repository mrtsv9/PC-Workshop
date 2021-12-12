package com.example.pcworkshop.dao

import com.example.pcworkshop.models.employees.Employees
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EmployeesDao {

    @GET("employees")
    suspend fun getAllEmployees(): Response<List<Employees>>

    @GET("employees/{id}")
    suspend fun getClient(@Path("id") id: Int): Response<Employees>

}