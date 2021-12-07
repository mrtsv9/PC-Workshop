package com.example.pcworkshop.screen.clients

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pcworkshop.R
import com.example.pcworkshop.databinding.FragmentClientsBinding
import com.example.pcworkshop.screen.adapters.ClientsAdapter
import com.example.pcworkshop.services.Test
import com.example.pcworkshop.services.Test.clientsList

class ClientsFragment : Fragment() {

    private var binding: FragmentClientsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentClientsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ClientsAdapter(clientsList)
        binding?.rvClients?.layoutManager = LinearLayoutManager(binding?.root?.context,
            LinearLayoutManager.VERTICAL, false)
        binding?.rvClients?.adapter = adapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}