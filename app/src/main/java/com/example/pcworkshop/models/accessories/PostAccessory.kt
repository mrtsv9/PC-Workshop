package com.example.pcworkshop.models.accessories

import com.google.gson.annotations.SerializedName

data class PostAccessory(
    var name: String = "",
    var properties: String = "",
    var price: Int = 0,
    @SerializedName("types_of_accessories_id")
    var typeOfAccessoriesId: Int = 0
)