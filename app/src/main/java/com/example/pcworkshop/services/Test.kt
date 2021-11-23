package com.example.pcworkshop.services

import android.util.Log
import com.example.pcworkshop.clients.Client
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Test : Callback<List<Client>> {

    private const val URL = "http://10.0.2.2:3000/"
    private var clientsList: MutableList<Client> = emptyList<Client>().toMutableList()

    fun start() {
        val gson: Gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val myApi = retrofit.create(MyAPI::class.java)

        val call = myApi.getAllClients()
        call.enqueue(this)
    }

    override fun onResponse(call: Call<List<Client>>, response: Response<List<Client>>) {
        if(response.isSuccessful) {
            val clients = response.body()
            if(clients != null) {
                for (i in 0 until clients.count()) {
                    val clientId = clients[i].clientId
                    val firstName = clients[i].firstName
                    val lastName = clients[i].lastName
                    val email = clients[i].email
                    val phoneNumber = clients[i].phoneNumber
                    clientsList.add(Client(clientId, firstName, lastName, email, phoneNumber))
//                    Log.e("KEK", Client(clientId, firstName, lastName, email, phoneNumber).toString())
                }
            }
            Log.e("KEK", clientsList.toString())

        }
        else {
            Log.e("KEK", "govno")
        }
    }

    override fun onFailure(call: Call<List<Client>>, t: Throwable) {
        Log.e("KEK", t.toString())
    }

}