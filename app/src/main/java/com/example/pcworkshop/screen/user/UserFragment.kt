package com.example.pcworkshop.screen.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pcworkshop.databinding.FragmentUserBinding
import com.example.pcworkshop.models.clients.Clients
import com.example.pcworkshop.models.orders.Orders
import com.example.pcworkshop.models.pc.Pc
import com.example.pcworkshop.screen.accessories.view_models.AccessoriesViewModel
import com.example.pcworkshop.screen.clients.view_models.ClientsViewModel
import com.example.pcworkshop.screen.employees.view_models.EmployeesViewModel
import com.example.pcworkshop.screen.orders.view_models.OrdersViewModel
import com.example.pcworkshop.screen.pc.adapters.PcAdapter
import com.example.pcworkshop.screen.pc.view_models.PcViewModel
import com.example.pcworkshop.screen.pc_accessories.view_models.PcAccessoriesViewModel

class UserFragment : Fragment() {

    private var binding: FragmentUserBinding? = null
    private val pcViewModel: PcViewModel by viewModels()
    private val ordersViewModel: OrdersViewModel by viewModels()
    private val clientsViewModel: ClientsViewModel by viewModels()
    private val employeesViewModel: EmployeesViewModel by viewModels()
    private val accessoriesViewModel: AccessoriesViewModel by viewModels()
    private val pcAccessoriesViewModel: PcAccessoriesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.btnClientAddOrder?.setOnClickListener {
            clickListener()
        }

        pcViewModel.getAllPc()
        ordersViewModel.getAllOrders()
        clientsViewModel.getAllClients()
        employeesViewModel.getAllEmployees()
        accessoriesViewModel.getAllAccessories()
        pcAccessoriesViewModel.getAllPcAccessories()

    }

    override fun onResume() {
        super.onResume()

        val adapter = PcAdapter()
        binding?.rvUserPc?.layoutManager = LinearLayoutManager(
            binding?.root?.context,
            LinearLayoutManager.VERTICAL, false
        )
        binding?.rvUserPc?.adapter = adapter

        val listOfPcToDisplay: MutableList<Pc> = emptyList<Pc>().toMutableList()
        val listOfOrdersToDisplay: MutableList<Orders> = emptyList<Orders>().toMutableList()
        var displayClient: Clients? = null
        employeesViewModel.employeesLiveData.observe(viewLifecycleOwner) { employees ->
            clientsViewModel.clientsLiveData.observe(viewLifecycleOwner) { clients ->
                clients.forEach { client ->
                    if (client.email == loggedUserEmail)
                        displayClient = client
                }
                ordersViewModel.ordersLiveData.observe(viewLifecycleOwner) { orders ->
                    orders.forEach {
                        if (it.clientId == displayClient?.clientId)
                            listOfOrdersToDisplay.add(it)
                    }
                    accessoriesViewModel.accessoriesLiveData.observe(viewLifecycleOwner) { accessories ->
                        pcAccessoriesViewModel.accessoriesLiveData.observe(viewLifecycleOwner) { pcAccessories ->
                            pcViewModel.pcLiveData.observe(viewLifecycleOwner) { computers ->
                                computers.forEach { pc ->
                                    listOfOrdersToDisplay.forEach { order ->
                                        if (pc.orderId == order.orderId) {
                                            listOfPcToDisplay.add(pc)
                                        }
                                    }
                                }
                                adapter.setData(listOfPcToDisplay, clients, orders, employees, pcAccessories,accessories)
                            }
                        }
                    }
                }
            }
        }
        listOfOrdersToDisplay.clear()
        listOfPcToDisplay.clear()
        displayClient = null
    }

    private fun clickListener() {
        findNavController().navigate(UserFragmentDirections.actionUserFragmentToUserAddingFragment())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        var loggedUserEmail: String? = ""

    }
}