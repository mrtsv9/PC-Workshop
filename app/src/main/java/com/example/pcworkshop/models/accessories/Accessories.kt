package com.example.pcworkshop.models.accessories

import com.google.gson.annotations.SerializedName

data class Accessories(
    var accessoriesId: Int = 0,
    var name: String = "",
    var properties: String = "",
    var price: Int = 0,
    var typeOfAccessoriesId: Int = 0,
    val typeOfAccessories: TypeOfAccessories
)