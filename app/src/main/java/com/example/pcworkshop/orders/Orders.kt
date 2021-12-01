package com.example.pcworkshop.orders

data class Orders(
    var orderId: Int = 0,
    var address: String = "",
    var clientId: Int = 0,
    var deliveryMethodId: Int = 0,
    var delivery: DeliveryMethods = DeliveryMethods(),
    var paymentMethodId: Int = 0,

)

data class DeliveryMethods(
    var deliveryMethodId: Int = 0,
    var deliveryType: String  = ""
)

data class PaymentMethods(
    var paymentMethodId: Int = 0,
    var paymentType: String  = ""
)