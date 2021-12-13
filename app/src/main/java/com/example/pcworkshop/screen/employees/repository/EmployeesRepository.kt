package com.example.pcworkshop.screen.employees.repository

import com.example.pcworkshop.dao.EmployeesDao
import com.example.pcworkshop.models.employees.Employees
import com.example.pcworkshop.models.employees.PostEmployee
import com.example.pcworkshop.services.RetrofitInstance
import retrofit2.Call
import retrofit2.Response

class EmployeesRepository {

    suspend fun getAllEmployees(): Response<List<Employees>> {
        val retrofitInstance = RetrofitInstance.getRetrofitInstance().create(EmployeesDao::class.java)
        return retrofitInstance.getAllEmployees()
    }

    fun addEmployee(employee: PostEmployee): Call<PostEmployee> {
        val retrofitInstance = RetrofitInstance.getRetrofitInstance().create(EmployeesDao::class.java)
        return retrofitInstance.addEmployee(employee)
    }

}