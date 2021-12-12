package com.example.pcworkshop.models.pc

import com.google.gson.annotations.SerializedName

data class Pc(
    val pcId: Int = 0,
    val title: String = "",
    val orderId: Int = 0,
    val totalPrice: Int = 0,
    val assemblyTypeId: Int = 0,
    val assembly: AssemblyType = AssemblyType(),
    val employeeId: Int = 0
)


