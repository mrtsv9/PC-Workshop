package com.example.pcworkshop.screen.pc.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pcworkshop.models.pc.Pc
import com.example.pcworkshop.screen.pc.repository.PcRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PcViewModel: ViewModel() {

    private val repository = PcRepository()
    var pcLiveData: MutableLiveData<List<Pc>> = MutableLiveData()

    fun getAllPc() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getAllPc()
            pcLiveData.postValue(response.body())
        }
    }

}