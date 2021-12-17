package com.example.pcworkshop.screen.orders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pcworkshop.R
import com.example.pcworkshop.databinding.FragmentOrdersAddingBinding
import com.example.pcworkshop.models.orders.Orders
import com.example.pcworkshop.models.orders.PostOrder
import com.example.pcworkshop.screen.clients.adapters.SelectableClientsAdapter
import com.example.pcworkshop.screen.clients.view_models.ClientsViewModel
import com.example.pcworkshop.screen.orders.repository.OrdersRepository
import com.example.pcworkshop.screen.orders.view_models.OrdersViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrdersAddingFragment : Fragment() {

    private var binding: FragmentOrdersAddingBinding? = null
    private val clientsViewModel: ClientsViewModel by viewModels()
    private val viewModel: OrdersViewModel by viewModels()
    private var repository = OrdersRepository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrdersAddingBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fillDelivery()
        fillPayment()

        clientsViewModel.getAllClients()
        viewModel.getAllOrders()
//        viewModel.getAllOrders()

        if(orderToUpdate == null) {
            val adapter = SelectableClientsAdapter()
            binding?.rvSelectClients?.layoutManager = LinearLayoutManager(
                binding?.root?.context,
                LinearLayoutManager.VERTICAL, false
            )
            binding?.rvSelectClients?.adapter = adapter

            clientsViewModel.getAllClients()
//            viewModel.getAllOrders()
            clientsViewModel.clientsLiveData.observe(viewLifecycleOwner) {
                adapter.setData(it)
            }

            binding?.btnAddOrder?.setOnClickListener {
                addOrder()
            }
        } else {

            setFields()

            val adapter = SelectableClientsAdapter()
            binding?.rvSelectClients?.layoutManager = LinearLayoutManager(
                binding?.root?.context,
                LinearLayoutManager.VERTICAL, false
            )
            binding?.rvSelectClients?.adapter = adapter


            clientsViewModel.clientsLiveData.observe(viewLifecycleOwner) {
                adapter.setData(it)
            }

            binding?.btnAddOrder?.setOnClickListener {
                updateOrder()
                clearAll()
            }

            binding?.btnDeleteOrder?.setOnClickListener {
                deleteOrder()
                clearAll()
            }
        }
    }

    private fun fillDelivery() {
        val deliveryTypes = listOf<String>("Доставка", "Самовывоз")
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, deliveryTypes)
        binding?.actvDelivery?.setAdapter(arrayAdapter)
    }

    private fun fillPayment() {
        val paymentTypes = listOf<String>("Картой курьеру", "Картой онлайн", "Наличными")
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, paymentTypes)
        binding?.actvPayment?.setAdapter(arrayAdapter)
    }

    private fun addOrder() {
        val address = binding?.etAddingOrdersAddress?.text.toString()
        val delivery = binding?.actvDelivery?.text.toString()
        val payment = binding?.actvPayment?.text.toString()
        val clientId = selectedClientId
        if(
            clientId == 0 ||
            address.isEmpty() ||
            address.length >= 20 ||
            delivery.isEmpty() ||
            payment.isEmpty()
        ) {
            Toast.makeText(context, "Заполните все полня корректно! ", Toast.LENGTH_SHORT).show()
        }
        else {

            val order = PostOrder(address, selectedClientId, getDeliveryMethodId(delivery), getPaymentMethodId(payment))
            val call = repository.addOrder(order)

            selectedClientId = 0
            checker = false

            call.enqueue(object: Callback<PostOrder> {
                override fun onResponse(call: Call<PostOrder>, response: Response<PostOrder>) {
                    Toast.makeText(context, "Заказ успешно добавлен! ", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                }

                override fun onFailure(call: Call<PostOrder>, t: Throwable) {
                    Toast.makeText(context, "Заказ успешно добавлен! ", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                }
            })
        }
    }

    private fun updateOrder() {
        val address = binding?.etAddingOrdersAddress?.text.toString()
        val delivery = binding?.actvDelivery?.text.toString()
        val payment = binding?.actvPayment?.text.toString()
        val clientId = selectedClientId
        if(
            clientId == 0 ||
            address.isEmpty() ||
            address.length >= 20 ||
            delivery.isEmpty() ||
            payment.isEmpty()
        ) {
            Toast.makeText(context, "Заполните все полня корректно! ", Toast.LENGTH_SHORT).show()
        }
        else {
            orderToUpdate?.let {
                repository.updateOrder(
                    it.orderId, address, clientId,
                    getPaymentMethodId(payment), getDeliveryMethodId(delivery)
                )
            }?.enqueue(object : Callback<PostOrder> {

                override fun onResponse(call: Call<PostOrder>, response: Response<PostOrder>) {
                    Toast.makeText(context, "Заказ успешно изменен! ", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                }

                override fun onFailure(call: Call<PostOrder>, t: Throwable) {
                    Toast.makeText(context, "Заказ успешно изменен! ", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                }
            })
        }
    }

    private fun deleteOrder() {
        orderToUpdate?.let { repository.deleteOrder(it.orderId) }?.enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                Toast.makeText(context, "Заказ успешно удален! ", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Toast.makeText(context, "Заказ успешно удален! ", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            }
        })
    }

    private fun getDeliveryMethodId(type: String): Int {
        var deliveryMethodId = 0
        val orders = viewModel.ordersLiveData
        orders.observe(viewLifecycleOwner) {
            it.forEach { orders ->
                if (orders.delivery.deliveryType == type) {
                    deliveryMethodId =  orders.deliveryMethodId
                }
            }
        }
        return deliveryMethodId
    }

    private fun getPaymentMethodId(type: String): Int {
        var paymentMethodId = 0
        val orders = viewModel.ordersLiveData
        orders.observe(viewLifecycleOwner) {
            it.forEach { orders ->
                if (orders.payment.paymentType == type) {
                    paymentMethodId =  orders.paymentMethodId
                }
            }
        }
        return paymentMethodId
    }

    private fun setFields() {
        binding?.btnAddOrder?.text = "Редактировать заказ"
        binding?.btnDeleteOrder?.visibility = View.VISIBLE

        binding?.etAddingOrdersAddress?.setText(orderToUpdate?.address)
        var deliveryMethod = ""
        deliveryMethod = if (orderToUpdate?.deliveryMethodId == 1) {
            "Доставка"
        } else "Самовывоз"
        binding?.actvDelivery?.setText(deliveryMethod)
        var paymentMethod = ""
        when (orderToUpdate?.paymentMethodId) {
            1 -> {
                paymentMethod = "Картой курьеру"
            }
            2 -> {
                paymentMethod =  "Картой онлайн"
            }
            3 -> paymentMethod =  "Наличными"
        }
        fillDelivery()
        fillPayment()
        binding?.actvPayment?.setText(paymentMethod)
        selectedClientId = orderToUpdate?.clientId ?: 0
    }

    private fun clearAll() {
        binding?.btnAddOrder?.text = "Добавить заказ"
        binding?.btnAddOrder?.visibility = View.GONE
        selectedClientId = 0
        checker = false
        orderToUpdate = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        clearAll()
        binding = null
    }

    companion object {
        var selectedClientId = 0
        var checker = false

        var orderToUpdate: Orders? = null
    }
}