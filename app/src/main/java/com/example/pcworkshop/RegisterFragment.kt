package com.example.pcworkshop


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pcworkshop.databinding.FragmentRegisterBinding
import com.example.pcworkshop.models.Client
import com.example.pcworkshop.services.MyAPI
import com.example.pcworkshop.services.ServiceBuilder
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class RegisterFragment: Fragment() {

    private var binding: FragmentRegisterBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btn = binding!!.btnSingIn
        btn.setOnClickListener {
            findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment3())
        }

    }

    override fun onResume() {
        super.onResume()
        test()
    }

//    private fun test() {
//        val service = ServiceBuilder.buildService(MyAPI::class.java)
//
//        val requestCall: Call<List<Client>> =  service.getAllClients()
//
//        requestCall.enqueue(object: Callback<List<Client>> {
//            override fun onResponse(call: Call<List<Client>>, response: Response<List<Client>>) {
//                if (response.isSuccessful) {
//                    Log.e("KEK", "msg")
//                }
//            }
//
//            override fun onFailure(call: Call<List<Client>>, t: Throwable) {
//
//            }
//        })
//
//    }

    private fun test() {
        val client = OkHttpClient()
        val credential = Credentials.basic("root", "root")
        val request = Request.Builder()
            .url("http://10.0.2.2:3000/")
            .addHeader("Authorization", credential)
            .build()

//        client.newCall(request).enqueue(object : Callback<List<Client>>, okhttp3.Callback {
//            override fun onFailure(call: Call<List<Client>>, t: Throwable) {
//                Log.e("KEK", call.toString())
//            }
//
//            override fun onResponse(call: Call<List<Client>>, response: Response<List<Client>>) {
//                Log.e("KEK", "msg")
//            }
//
//            override fun onFailure(call: okhttp3.Call, e: IOException) {
//                Log.e("KEK", call.toString())
//            }
//
//            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
//                Log.e("KEK", "msg")
//            }
//        })

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}