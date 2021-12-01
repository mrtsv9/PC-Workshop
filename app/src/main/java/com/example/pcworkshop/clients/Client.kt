package com.example.pcworkshop.clients

import com.google.gson.annotations.SerializedName

data class Client(
    @SerializedName("client_id")
    var clientId: Int = 0,
    @SerializedName("first_name")
    var firstName: String = "",
    @SerializedName("last_name")
    var lastName: String = "",
    var email: String = "",
    @SerializedName("phone_number")
    var phoneNumber: String? = null
)