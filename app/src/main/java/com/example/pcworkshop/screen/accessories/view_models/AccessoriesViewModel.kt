package com.example.pcworkshop.screen.accessories.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pcworkshop.models.accessories.Accessories
import com.example.pcworkshop.screen.accessories.repository.AccessoriesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AccessoriesViewModel: ViewModel() {

    private val repository = AccessoriesRepository()
    var accessoriesLiveData: MutableLiveData<List<Accessories>> = MutableLiveData()

    fun getAllAccessories() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getAllAccessories()
            accessoriesLiveData.postValue(response.body())
        }
    }

}