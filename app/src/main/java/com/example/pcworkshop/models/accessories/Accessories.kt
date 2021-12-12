package com.example.pcworkshop.models.accessories

data class Accessories(
    var accessoriesId: Int = 0,
    var name: String = "",
    var properties: String = "",
    var price: Int = 0,
    var typeOfAccessoriesId: Int = 0,
    val typeOfAccessories: TypeOfAccessories,
)