package com.example.pcworkshop.screen.orders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pcworkshop.R
import com.example.pcworkshop.databinding.FragmentOrdersBinding
import com.example.pcworkshop.models.clients.Client
import com.example.pcworkshop.screen.clients.view_models.ClientsViewModel
import com.example.pcworkshop.screen.orders.adapters.OrdersAdapter
import com.example.pcworkshop.screen.orders.view_models.OrdersViewModel

class OrdersFragment : Fragment() {

    private var binding: FragmentOrdersBinding? = null
    private val viewModel: OrdersViewModel by viewModels()
    private val clientsViewModel: ClientsViewModel by viewModels()

    private var clients = emptyList<Client>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrdersBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = OrdersAdapter()
        binding?.rvOrders?.layoutManager = LinearLayoutManager(binding?.root?.context,
            LinearLayoutManager.VERTICAL, false)
        binding?.rvOrders?.adapter = adapter

        clientsViewModel.getAllClients()
        clientsViewModel.clientsLiveData.observe(viewLifecycleOwner) {
            clients = it
        }

        viewModel.getAllOrders()
        viewModel.ordersLiveData.observe(viewLifecycleOwner) {
            adapter.setData(it, clients)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}