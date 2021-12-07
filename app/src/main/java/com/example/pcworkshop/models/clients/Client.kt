package com.example.pcworkshop.models.clients

import com.google.gson.annotations.SerializedName

data class Client(
    var clientId: Int,
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var phoneNumber: String? = null,
    var password: String? = null
)