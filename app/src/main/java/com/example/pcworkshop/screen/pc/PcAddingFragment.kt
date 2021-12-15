package com.example.pcworkshop.screen.pc

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pcworkshop.R
import com.example.pcworkshop.databinding.FragmentPcAddingBinding
import com.example.pcworkshop.models.accessories.Accessories
import com.example.pcworkshop.models.pc.Pc
import com.example.pcworkshop.models.pc.PostPc
import com.example.pcworkshop.models.pc_accessories.PcAccessories
import com.example.pcworkshop.models.pc_accessories.PostPcAccessories
import com.example.pcworkshop.models.types_of_accessories.TypesOfAccessories
import com.example.pcworkshop.screen.accessories.adapters.PcAccessoriesAdapter
import com.example.pcworkshop.screen.accessories.view_models.AccessoriesViewModel
import com.example.pcworkshop.screen.clients.adapters.SelectableClientsAdapter
import com.example.pcworkshop.screen.employees.adapters.PcEmployeesAdapter
import com.example.pcworkshop.screen.employees.view_models.EmployeesViewModel
import com.example.pcworkshop.screen.pc.repository.PcRepository
import com.example.pcworkshop.screen.pc.view_models.PcViewModel
import com.example.pcworkshop.screen.pc_accessories.repository.PcAccessoriesRepository
import com.example.pcworkshop.screen.types_of_accessories.view_models.TypesOfAccessoriesViewModel
import org.koin.android.ext.android.bind
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PcAddingFragment : Fragment() {

    private var binding: FragmentPcAddingBinding? = null
    private val employeesViewModel: EmployeesViewModel by viewModels()
    private val accessoriesViewModel: AccessoriesViewModel by viewModels()
    private val typesViewModel: TypesOfAccessoriesViewModel by viewModels()
    private val viewModel: PcViewModel by viewModels()
    private val repository = PcRepository()
    private val pcAccessoriesRepository = PcAccessoriesRepository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPcAddingBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fillTypes()

        binding?.btnAddPc?.setOnClickListener {
            clickListener()
        }

        val employeesAdapter  = PcEmployeesAdapter()
        binding?.rvPcEmployees?.layoutManager = LinearLayoutManager(binding?.root?.context,
            LinearLayoutManager.VERTICAL, false)
        binding?.rvPcEmployees?.adapter = employeesAdapter

        employeesViewModel.getAllEmployees()
        employeesViewModel.employeesLiveData.observe(viewLifecycleOwner) {
            employeesAdapter.setData(it)
        }

        typesViewModel.getAllTypesOfAccessories()
        typesViewModel.typesOfAccessoriesLiveData.observe(viewLifecycleOwner) {
            types = it.toMutableList()
        }

        val accessoriesAdapter  = PcAccessoriesAdapter()
        binding?.rvPcAccessories?.layoutManager = LinearLayoutManager(binding?.root?.context,
            LinearLayoutManager.VERTICAL, false)
        binding?.rvPcAccessories?.adapter = accessoriesAdapter

        accessoriesViewModel.getAllAccessories()
        accessoriesViewModel.accessoriesLiveData.observe(viewLifecycleOwner) {
            accessoriesAdapter.setData(it)
        }

        tempTitle = ""
        tempAccessoriesList.clear()
        myAccessory = null
        types.clear()
        tempEmployeeEmail = ""
        employeeChecker = false
    }

    private fun clickListener() {
        val title = binding?.etAddingPcTitle?.text.toString()
        var orderId: Int = 0
        orderId = binding?.etAddingPcOrder?.text.toString().toInt()
        val assemblyTypeId = if(binding?.actvAssemblyType?.text.toString() == "Готовая сборка") {
            1
        } else 2
        var employeeId = 0
        employeesViewModel.employeesLiveData.observe(viewLifecycleOwner) {
            it.forEach { employee ->
                if(employee.email == tempEmployeeEmail) {
                    employeeId = employee.employeeId
                }
            }
        }
        if (title.isEmpty() ||
            orderId == 0 ||
            assemblyTypeId == 0 ||
            employeeId == 0 ||
            tempAccessoriesList.isNullOrEmpty()) {
            Toast.makeText(context,  "Заполните все полня корректно!", Toast.LENGTH_SHORT).show()
        }
        else {
            tempTitle = title
            val call = repository.addPc(PostPc(orderId, title, 0, assemblyTypeId, employeeId))

            call.enqueue(object: Callback<PostPc> {
                override fun onResponse(call: Call<PostPc>, response: Response<PostPc>) {
                    Toast.makeText(context, "Комьютер был успешно добавлен", Toast.LENGTH_SHORT).show()
                    addPcAccessories(tempTitle)
                }

                override fun onFailure(call: Call<PostPc>, t: Throwable) {
                    Toast.makeText(context, "Комьютер был успешно добавлен", Toast.LENGTH_SHORT).show()
                    addPcAccessories(tempTitle)
                }
            })
//            addPcAccessories(title)
//            var pcId = 0
//            var pcList: MutableList<Pc> = emptyList<Pc>().toMutableList()
//            viewModel.getAllPc()
//            viewModel.pcLiveData.observe(viewLifecycleOwner) {
//                it.forEach {
//                    if (it.title == title) {
//                        pcId = it.pcId
//                    }
//                }
//                pcList.addAll(it)
//                pcList.forEach {
//                    if (it.title == title) {
//                        pcId = it.pcId
//                    }
//                }
//                tempAccessoriesList.forEach { accessories ->
//                    val callPcAccessories = pcAccessoriesRepository
//                        .addPcAccessories(PostPcAccessories(pcId, accessories.accessoriesId))
//                    callPcAccessories.enqueue(object: Callback<PostPcAccessories> {
//                        override fun onResponse(
//                            call: Call<PostPcAccessories>,
//                            response: Response<PostPcAccessories>
//                        ) {
//
//                        }
//
//                        override fun onFailure(call: Call<PostPcAccessories>, t: Throwable) {
//
//                        }
//                    })
//                }
//            }

        }
    }

    private fun addPcAccessories(title: String) {

        var pcId = 0
        viewModel.getAllPc()
        viewModel.pcLiveData.observe(viewLifecycleOwner) {
            it.forEach {
                if (it.title == title) {
                    pcId = it.pcId
                    tempAccessoriesList.forEach { accessory ->
                        val call = pcAccessoriesRepository.addPcAccessories(PostPcAccessories(pcId, accessory.accessoriesId))

                        call.enqueue(object: Callback<PostPcAccessories> {
                            override fun onResponse(
                                call: Call<PostPcAccessories>,
                                response: Response<PostPcAccessories>
                            ) {
                                Log.e("KEK", response.body().toString())
                            }

                            override fun onFailure(call: Call<PostPcAccessories>, t: Throwable) {
                                Log.e("KEK", "govno2")
                            }
                        })
                    }
                }
            }
        }

    }

    private fun fillTypes() {
        var list = listOf<String>("Готовая сборка", "Выбор комплектующих")
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, list)
        binding?.actvAssemblyType?.setAdapter(arrayAdapter)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        var tempTitle = ""
        var tempAccessoriesList: MutableList<Accessories> = emptyList<Accessories>().toMutableList()
        var myAccessory: Accessories? = null
        var types: MutableList<TypesOfAccessories> = emptyList<TypesOfAccessories>().toMutableList()
        var tempEmployeeEmail = ""
        var employeeChecker = false
    }

}