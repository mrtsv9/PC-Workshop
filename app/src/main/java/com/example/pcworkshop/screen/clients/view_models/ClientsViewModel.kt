package com.example.pcworkshop.screen.clients.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pcworkshop.models.clients.Client
import com.example.pcworkshop.repository.ClientsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ClientsViewModel: ViewModel() {

    private val repository = ClientsRepository()
    var clientsLiveData: MutableLiveData<List<Client>> = MutableLiveData()

    fun getAllClients() {
        viewModelScope.launch(Dispatchers.IO) {
//            val retroInstance = RetrofitInstance.getRetroInstance().create(ClientsDao::class.java)
//            val response  = retroInstance.getAllClients()
            val response = repository.getAllClients()
            clientsLiveData.postValue(response.body())
        }
    }

}