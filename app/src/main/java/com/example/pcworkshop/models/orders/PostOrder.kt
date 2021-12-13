package com.example.pcworkshop.models.orders

import com.google.gson.annotations.SerializedName

data class PostOrder(
    var address: String = "",
    @SerializedName("client_id")
    var clientId: Int = 0,
    @SerializedName("payment_method_id")
    var paymentMethodId: Int = 0,
    @SerializedName("delivery_method_id")
    var deliveryMethodId: Int = 0
)