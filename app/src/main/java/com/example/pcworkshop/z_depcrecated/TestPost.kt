//package com.example.pcworkshop.z_depcrecated
//
//import android.util.Log
//import com.example.pcworkshop.models.clients.Client
//import com.google.gson.Gson
//import com.google.gson.GsonBuilder
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//class TestPost(private val newClient: Client) {
//
//    private val URL = "http://10.0.2.2:3000/"
//    private var clientsList: MutableList<Client> = emptyList<Client>().toMutableList()
//
//    fun start() {
//        val gson: Gson = GsonBuilder()
//            .setLenient()
//            .create()
//
//        val retrofit = Retrofit.Builder()
//            .baseUrl(URL)
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .build()
//
//        val myApi = retrofit.create(MyAPI::class.java)
//
//        val call = myApi.addClient(newClient)
//
//        call.enqueue(object : Callback<Client> {
//            override fun onResponse(call: Call<Client>, response: Response<Client>) {
//                if(response.isSuccessful) {
//
//                }
//                else {
//                    Log.e("KEK", "govno")
//                }
//            }
//
//            override fun onFailure(call: Call<Client>, t: Throwable) {
//                Log.e("KEK", "govno2")
//            }
//        })
//
//    }
//
//
//}