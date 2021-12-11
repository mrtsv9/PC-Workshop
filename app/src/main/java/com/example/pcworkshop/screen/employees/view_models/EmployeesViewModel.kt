package com.example.pcworkshop.screen.employees.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pcworkshop.models.employees.Employees
import com.example.pcworkshop.screen.employees.repository.EmployeesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EmployeesViewModel: ViewModel() {

    private val repository = EmployeesRepository()
    var employeesLiveData: MutableLiveData<List<Employees>> = MutableLiveData()

    fun getAllEmployees() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getAllEmployees()
            employeesLiveData.postValue(response.body())
        }
    }

}