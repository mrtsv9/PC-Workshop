package com.example.pcworkshop.models.pc

import com.google.gson.annotations.SerializedName

data class PostPc(
    @SerializedName("order_id")
    val orderId: Int = 0,
    val title: String = "",
    @SerializedName("total_price")
    val totalPrice: Int = 0,
    @SerializedName("assembly_type_id")
    val assemblyTypeId: Int = 0,
    @SerializedName("employee_id")
    val employeeId: Int = 0
)


