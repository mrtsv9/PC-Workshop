package com.example.pcworkshop.screen.positions.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pcworkshop.models.positions.Position
import com.example.pcworkshop.screen.positions.repository.PositionsRepository
import com.example.pcworkshop.screen.types_of_accessories.repository.TypesOfAccessoriesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PositionsViewModel: ViewModel() {

    private val repository = PositionsRepository()
    var positionsLiveData: MutableLiveData<List<Position>> = MutableLiveData()

    fun getAllPositions() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getAllPositions()
            positionsLiveData.postValue(response.body())
        }
    }

}