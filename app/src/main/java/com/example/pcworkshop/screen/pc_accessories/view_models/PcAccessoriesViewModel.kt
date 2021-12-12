package com.example.pcworkshop.screen.pc_accessories.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pcworkshop.models.orders.Orders
import com.example.pcworkshop.models.pc_accessories.PcAccessories
import com.example.pcworkshop.screen.pc_accessories.repository.PcAccessoriesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PcAccessoriesViewModel: ViewModel() {

    private val repository = PcAccessoriesRepository()

    var accessoriesLiveData: MutableLiveData<List<PcAccessories>> = MutableLiveData()

    fun getAllPcAccessories() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getAllPcAccessories()
            accessoriesLiveData.postValue(response.body())
        }
    }

}