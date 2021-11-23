package com.example.pcworkshop.accessories

data class Accessories(
    var accessoriesId: Int = 0,
    var name: String = "",
    var properties: String = "",
    var typeOfAccessoriesId: Int = 0,
    var typeOfAccessories: TypeOfAccessories = TypeOfAccessories()
)

data class TypeOfAccessories(
    var typeOfAccessoriesId: Int = 0,
    var type: String = ""
)