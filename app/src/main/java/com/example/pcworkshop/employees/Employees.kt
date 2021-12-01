package com.example.pcworkshop.employees

import com.example.pcworkshop.positions.Position

data class Employees(
    var employeeId: Int = 0,
    var firstName: String = "",
    var lastName: String = "",
    var middleName: String = "",
    var address: String = "",
    var phone_number: String = "",
    var email: String = "",
    var positionId: Int = 0,
    val position: Position
)