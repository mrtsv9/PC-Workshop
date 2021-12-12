package com.example.pcworkshop.screen.pc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pcworkshop.databinding.FragmentPcBinding
import com.example.pcworkshop.screen.accessories.view_models.AccessoriesViewModel
import com.example.pcworkshop.screen.clients.view_models.ClientsViewModel
import com.example.pcworkshop.screen.employees.view_models.EmployeesViewModel
import com.example.pcworkshop.screen.orders.view_models.OrdersViewModel
import com.example.pcworkshop.screen.pc.adapters.PcAdapter
import com.example.pcworkshop.screen.pc.view_models.PcViewModel
import com.example.pcworkshop.screen.pc_accessories.view_models.PcAccessoriesViewModel

class PcFragment : Fragment() {

    private var binding: FragmentPcBinding? = null
    private val viewModel: PcViewModel by viewModels()
    private val ordersViewModel: OrdersViewModel by viewModels()
    private val clientsViewModel: ClientsViewModel by viewModels()
    private val employeesViewModel: EmployeesViewModel by viewModels()
    private val accessoriesViewModel: AccessoriesViewModel by viewModels()
    private val pcAccessoriesViewModel: PcAccessoriesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPcBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = PcAdapter()
        binding?.rvPc?.layoutManager = LinearLayoutManager(
            binding?.root?.context,
            LinearLayoutManager.VERTICAL, false
        )
        binding?.rvPc?.adapter = adapter

        viewModel.getAllPc()
        ordersViewModel.getAllOrders()
        clientsViewModel.getAllClients()
        employeesViewModel.getAllEmployees()
        accessoriesViewModel.getAllAccessories()
        pcAccessoriesViewModel.getAllPcAccessories()

        employeesViewModel.employeesLiveData.observe(viewLifecycleOwner) { employees ->
            clientsViewModel.clientsLiveData.observe(viewLifecycleOwner) { clients ->
                ordersViewModel.ordersLiveData.observe(viewLifecycleOwner) { orders ->
                    accessoriesViewModel.accessoriesLiveData.observe(viewLifecycleOwner) { accessories ->
                        pcAccessoriesViewModel.accessoriesLiveData.observe(viewLifecycleOwner) { pcAccessories ->
                            viewModel.pcLiveData.observe(viewLifecycleOwner) {
                                adapter.setData(it, clients, orders, employees, pcAccessories,accessories)
                            }
                        }
                    }
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}