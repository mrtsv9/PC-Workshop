package com.example.pcworkshop.screen.employees

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.pcworkshop.R
import com.example.pcworkshop.databinding.FragmentEmployeesAddingBinding
import com.example.pcworkshop.models.employees.PostEmployee
import com.example.pcworkshop.models.positions.Position
import com.example.pcworkshop.screen.employees.repository.EmployeesRepository
import com.example.pcworkshop.screen.positions.view_models.PositionsViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EmployeesAddingFragment : Fragment() {

    private var binding: FragmentEmployeesAddingBinding? = null
    private val repository = EmployeesRepository()
    private val positionsViewModel: PositionsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEmployeesAddingBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        positionsViewModel.getAllPositions()
        fillPositions()

        binding?.btnAddEmployee?.setOnClickListener {
            clickListener()
        }

    }

    private fun fillPositions() {
        var listOfPositions: MutableList<String> = emptyList<String>().toMutableList()
        positionsViewModel.positionsLiveData.observe(viewLifecycleOwner) { it ->
            it.forEach {
                listOfPositions.add(it.name)
            }
        }
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, listOfPositions)
        binding?.actvEmployeePosition?.setAdapter(arrayAdapter)
    }

    private fun clickListener() {
        val name = binding?.etAddingEmployeesName?.text.toString()
        val lastName = binding?.etAddingEmployeesLastName?.text.toString()
        val middleName = binding?.etAddingEmployeesMiddleName?.text.toString()
        val address = binding?.etAddingEmployeesAddress?.text.toString()
        val email = binding?.etAddingEmployeesEmail?.text.toString()
        val number = binding?.etAddingEmployeesPhone?.text.toString()
        val password = binding?.etAddingEmployeesPassword?.text.toString()
        val positionName = binding?.actvEmployeePosition?.text.toString()
        var positionId = 0

        val validationList = listOf<String>(name, lastName, middleName, address,email,number, password, positionName)
        if (checkFieldsValidation(validationList)) {
            positionsViewModel.positionsLiveData.observe(viewLifecycleOwner) {
                it.forEach { position ->
                    if(position.name == positionName) {
                        positionId = position.positionId
                    }
                }
            }
            val employee = PostEmployee(name, lastName, middleName, address, email, number, password, positionId)
            val call = repository.addEmployee(employee)

            call.enqueue(object: Callback<PostEmployee> {
                override fun onResponse(call: Call<PostEmployee>, response: Response<PostEmployee>) {
                    Toast.makeText(context, "Сотрудник успешно добавлен!", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                }

                override fun onFailure(call: Call<PostEmployee>, t: Throwable) {
                    Toast.makeText(context, "Сотрудник успешно добавлен!", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
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