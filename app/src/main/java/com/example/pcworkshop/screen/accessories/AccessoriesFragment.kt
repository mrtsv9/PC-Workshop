package com.example.pcworkshop.screen.accessories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pcworkshop.R
import com.example.pcworkshop.databinding.FragmentAccessoriesBinding
import com.example.pcworkshop.models.accessories.PostAccessory
import com.example.pcworkshop.screen.accessories.adapters.AccessoriesAdapter
import com.example.pcworkshop.screen.accessories.repository.AccessoriesRepository
import com.example.pcworkshop.screen.accessories.view_models.AccessoriesViewModel
import com.example.pcworkshop.screen.clients.adapters.SelectableClientsAdapter
import com.example.pcworkshop.screen.types_of_accessories.view_models.TypesOfAccessoriesViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccessoriesFragment : Fragment() {

    private var binding: FragmentAccessoriesBinding? = null
    private val repository = AccessoriesRepository()
    private val viewModel: AccessoriesViewModel by viewModels()
    private val typesOfAccessoriesViewModel: TypesOfAccessoriesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccessoriesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllAccessories()
        typesOfAccessoriesViewModel.getAllTypesOfAccessories()

        fillTypeOfAccessories()

        viewModel.getAllAccessories()
        viewModel.accessoriesLiveData.observe(viewLifecycleOwner) {
            val adapter  = AccessoriesAdapter(it)
            adapter.setData(it)
            binding?.rvAccessoriesList?.layoutManager = LinearLayoutManager(binding?.root?.context,
                LinearLayoutManager.VERTICAL, false)
            binding?.rvAccessoriesList?.adapter = adapter
        }

        binding?.btnAddAccessory?.setOnClickListener {
            clickListener()
        }
    }

    private fun fillTypeOfAccessories() {
        val typesList: MutableList<String> = emptyList<String>().toMutableList()
        typesOfAccessoriesViewModel.positionsLiveData.observe(viewLifecycleOwner) {
            it.forEach {
                typesList.add(it.type)
            }
        }
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, typesList)
        binding?.actvAccessoryType?.setAdapter(arrayAdapter)
    }

    private fun clickListener() {
        val name = binding?.etAddingAccessoryName?.text.toString()
        val properties = binding?.etAddingAccessoryProperties?.text.toString()
        val price = binding?.etAddingAccessoryPrice?.text.toString()
        val typesOfAccessories = binding?.actvAccessoryType?.text.toString()
        val validationList = listOf<String>(name, properties, price, typesOfAccessories)

        if (checkFieldsValidation(validationList)) {
            var typeOfAccessoryId = 0
            typesOfAccessoriesViewModel.positionsLiveData.observe(viewLifecycleOwner) {
                it.forEach {
                    if(it.type == typesOfAccessories) {
                        typeOfAccessoryId = it.typeOfAccessoryId
                    }
                }
            }
            val call = repository.addAccessory(PostAccessory(name, properties, price.toInt(), typeOfAccessoryId))

            call.enqueue(object: Callback<PostAccessory> {
                override fun onResponse(
                    call: Call<PostAccessory>,
                    response: Response<PostAccessory>
                ) {
                    Toast.makeText(context, "Комплектующая успешно добавлена!", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<PostAccessory>, t: Throwable) {
                    Toast.makeText(context, "Комплектующая успешно добавлена!", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }


    private fun checkFieldsValidation(list: List<String>): Boolean {
        list.forEach {
            if(it.isEmpty() || it.length > 20) {
                Toast.makeText(context,  "Заполните все полня корректно!", Toast.LENGTH_SHORT).show()
                return false
            }
        }
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}