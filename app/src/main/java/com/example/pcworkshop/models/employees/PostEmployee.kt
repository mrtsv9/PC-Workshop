package com.example.pcworkshop.models.employees

import com.example.pcworkshop.models.positions.Position
import com.google.gson.annotations.SerializedName

data class PostEmployee(
    @SerializedName("first_name")
    var firstName: String = "",
    @SerializedName("last_name")
    var lastName: String = "",
    @SerializedName("middle_name")
    var middleName: String = "",
    var address: String = "",
    @SerializedName("phone_number")
    var phoneNumber: String = "",
    var email: String = "",
    var password: String = "",
    @SerializedName("position_id")
    var positionId: Int = 0,
)