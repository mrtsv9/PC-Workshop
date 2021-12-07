package com.example.pcworkshop.models.orders

data class Orders(
    var orderId: Int = 0,
    var address: String = "",
    var clientId: Int = 0,
    var deliveryMethodId: Int = 0,
    val delivery: DeliveryMethods,
    var paymentMethodId: Int = 0,
    val payment: PaymentMethods
)