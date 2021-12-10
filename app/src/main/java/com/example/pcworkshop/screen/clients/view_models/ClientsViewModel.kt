package com.example.pcworkshop.screen.clients.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pcworkshop.models.clients.Clients
import com.example.pcworkshop.screen.clients.repository.ClientsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ClientsViewModel: ViewModel() {

    private val repository = ClientsRepository()
    var clientsLiveData: MutableLiveData<List<Clients>> = MutableLiveData()
    var clientLiveData: MutableLiveData<Clients> = MutableLiveData()

    fun getAllClients() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getAllClients()
            clientsLiveData.postValue(response.body())
        }
    }

    fun getClient(clientId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getClient(clientId)
            clientLiveData.postValue(response.body())
        }
    }

}