package com.example.pcworkshop.screen.types_of_accessories.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pcworkshop.models.positions.Position
import com.example.pcworkshop.models.types_of_accessories.TypesOfAccessories
import com.example.pcworkshop.screen.types_of_accessories.repository.TypesOfAccessoriesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TypesOfAccessoriesViewModel: ViewModel() {

    private val repository = TypesOfAccessoriesRepository()
    var typesOfAccessoriesLiveData: MutableLiveData<List<TypesOfAccessories>> = MutableLiveData()

    fun getAllTypesOfAccessories() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getAllTypesOfAccessories()
            typesOfAccessoriesLiveData.postValue(response.body())
        }
    }

}