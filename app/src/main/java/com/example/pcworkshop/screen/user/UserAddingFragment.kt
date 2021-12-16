package com.example.pcworkshop.screen.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pcworkshop.R
import com.example.pcworkshop.databinding.FragmentUserAddingBinding
import com.example.pcworkshop.screen.accessories.adapters.PcAccessoriesAdapter
import com.example.pcworkshop.screen.accessories.view_models.AccessoriesViewModel

class UserAddingFragment : Fragment() {

    private var binding: FragmentUserAddingBinding? = null
    private val accessoriesViewModel: AccessoriesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserAddingBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fillDelivery()
        fillPayment()
        fillTypes()

        val accessoriesAdapter = PcAccessoriesAdapter()
        binding?.rvUserAccessories?.layoutManager = LinearLayoutManager(
            binding?.root?.context,
            LinearLayoutManager.VERTICAL, false
        )
        binding?.rvUserAccessories?.adapter = accessoriesAdapter

        accessoriesViewModel.getAllAccessories()
        accessoriesViewModel.accessoriesLiveData.observe(viewLifecycleOwner) {
            accessoriesAdapter.setData(it)
        }

    }

    private fun fillDelivery() {
        val deliveryTypes = listOf<String>("Доставка", "Самовывоз")
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, deliveryTypes)
        binding?.actvUserDelivery?.setAdapter(arrayAdapter)
    }

    private fun fillPayment() {
        val paymentTypes = listOf<String>("Картой курьеру", "Картой онлайн", "Наличными")
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, paymentTypes)
        binding?.actvUserPayment?.setAdapter(arrayAdapter)
    }

    private fun fillTypes() {
        val list = listOf<String>("Готовая сборка", "Выбор комплектующих")
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, list)
        binding?.actvUserAssemblyType?.setAdapter(arrayAdapter)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}