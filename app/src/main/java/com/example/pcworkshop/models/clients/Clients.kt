package com.example.pcworkshop.models.clients

import com.google.gson.annotations.SerializedName

data class Clients(
    var clientId: Int,
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var phoneNumber: String? = null,
    var password: String? = null
)