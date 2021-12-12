package com.example.pcworkshop.screen.employees

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pcworkshop.databinding.FragmentEmployeesBinding
import com.example.pcworkshop.screen.employees.adapters.EmployeesAdapter
import com.example.pcworkshop.screen.employees.view_models.EmployeesViewModel

class EmployeesFragment : Fragment() {

    private var binding: FragmentEmployeesBinding? = null
    private val viewModel: EmployeesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEmployeesBinding.inflate(inflater,container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = EmployeesAdapter()
        binding?.rvEmployees?.layoutManager = LinearLayoutManager(binding?.root?.context,
            LinearLayoutManager.VERTICAL, false)
        binding?.rvEmployees?.adapter = adapter

        viewModel.getAllEmployees()
        viewModel.employeesLiveData.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}