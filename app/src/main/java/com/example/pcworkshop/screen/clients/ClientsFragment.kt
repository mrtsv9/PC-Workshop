package com.example.pcworkshop.screen.clients

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pcworkshop.databinding.FragmentClientsBinding
import com.example.pcworkshop.models.clients.Client
import com.example.pcworkshop.screen.clients.adapters.ClientsAdapter
import com.example.pcworkshop.screen.clients.view_models.ClientsViewModel

//import com.example.pcworkshop.services.Test
//import com.example.pcworkshop.services.Test.clientsList

class ClientsFragment : Fragment() {

    private var binding: FragmentClientsBinding? = null

//    private val viewModel: ClientsViewModel by viewModels()
    private val viewModel: ClientsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClientsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter  = ClientsAdapter()
        binding?.rvClients?.layoutManager = LinearLayoutManager(binding?.root?.context,
            LinearLayoutManager.VERTICAL, false)
        binding?.rvClients?.adapter = adapter

        viewModel.getAllClients()
        viewModel.clientsLiveData.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
//        viewModel.getClients()
//        viewModel.clientsLiveData.observe(viewLifecycleOwner) {
//            if(it == null) {
//                Log.e("KEK", "govno")
//                return@observe
//            }
//            else {
//                adapter.setData(it)
//            }
//        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun getClient(clientId: Int){
        viewModel.getClient(clientId)
    }

}