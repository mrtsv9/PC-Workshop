package com.example.pcworkshop.screen.orders.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pcworkshop.models.orders.Orders
import com.example.pcworkshop.screen.orders.repository.OrdersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrdersViewModel: ViewModel() {

    private val repository = OrdersRepository()
    var ordersLiveData: MutableLiveData<List<Orders>> = MutableLiveData()
//    var clientLiveData: MutableLiveData<Client> = MutableLiveData()

    fun getAllOrders() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getAllOrders()
            ordersLiveData.postValue(response.body())
        }
    }

//    fun getClient(clientId: Int) {
//        viewModelScope.launch(Dispatchers.IO) {
//            val response = repository.getClient(clientId)
//            clientLiveData.postValue(response.body())
//        }
//    }

}