package com.example.pcworkshop.repository

import android.util.Log
import com.example.pcworkshop.dao.ClientsDao
import com.example.pcworkshop.models.clients.Client
import com.example.pcworkshop.services.ServiceBuilder
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.moshi.Moshi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class ClientsRepository {

    fun getAllClients(): Call<List<Client>>? {

        val URL = "http://10.0.2.2:3000/"

        val gson: Gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val api = retrofit.create(ClientsDao::class.java)

        val call = api.getAllClients()

        val service = ServiceBuilder.buildService(ClientsDao::class.java)
        val request = service.getAllClients()
        var myResponse: List<Client> = emptyList()

//        if (request.isSuccessful) {
//            return request.body()
//        }

        call.enqueue(object: Callback<List<Client>> {
            override fun onResponse(call: Call<List<Client>>, response: Response<List<Client>>) {
                myResponse = response.body()!!
            }

            override fun onFailure(call: Call<List<Client>>, t: Throwable) {
                Log.e("KEK", "govnoo")
            }
        })

//        return if (myResponse.isNullOrEmpty()) {
//            null
//        } else myResponse
        return null
    }

}