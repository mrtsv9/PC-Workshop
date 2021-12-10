package com.example.pcworkshop.screen.clients

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pcworkshop.models.clients.Client
import com.example.pcworkshop.repository.ClientsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call

class ClientsViewModel: ViewModel() {

    private val repository = ClientsRepository()

    private val _clientsLiveData = MutableLiveData<Call<List<Client>>?>()
    val clientsLiveData: MutableLiveData<Call<List<Client>>?> = _clientsLiveData

    fun getClients() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getAllClients()
            _clientsLiveData.postValue(response)
        }
    }

}