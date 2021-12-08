package com.example.pcworkshop.screen.clients

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pcworkshop.models.clients.Client
import com.example.pcworkshop.repository.ClientsRepository
import kotlinx.coroutines.launch

class ClientsViewModel: ViewModel() {

    private val repository = ClientsRepository()

    private val _clientsLiveData = MutableLiveData<List<Client>?>()
    val clientsLiveData: MutableLiveData<List<Client>?> = _clientsLiveData

    fun getClients() {
        viewModelScope.launch {
            val response = repository.getAllClients()
            _clientsLiveData.postValue(response)
        }
    }

}