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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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

    private fun test() {
        val service = ServiceBuilder.buildService(MyAPI::class.java)

        val requestCall: Call<List<Client>> =  service.getAllClients()

        requestCall.enqueue(object: Callback<List<Client>> {
            override fun onResponse(call: Call<List<Client>>, response: Response<List<Client>>) {
                if (response.isSuccessful) {
                    Log.e("KEK", "msg")
                }
            }

            override fun onFailure(call: Call<List<Client>>, t: Throwable) {

            }
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}