package com.example.pcworkshop.clients

data class Client(
    var clientId: Int = 0,
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var phoneNumber: String? = null
)