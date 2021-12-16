package com.example.pcworkshop.models.employees

import com.example.pcworkshop.models.positions.Position
import com.google.gson.annotations.SerializedName

data class Employees(
    var employeeId: Int = 0,
    var firstName: String = "",
    var lastName: String = "",
    var middleName: String = "",
    var address: String = "",
    var phoneNumber: String = "",
    var email: String = "",
    var password: String = "",
    var positionId: Int = 0,
    val position: Position
)