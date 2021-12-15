package com.example.pcworkshop.models.pc_accessories

import com.google.gson.annotations.SerializedName

data class PostPcAccessories(
    @SerializedName("pc_id")
    val pcId: Int,
    @SerializedName("accessory_id")
    val accessoryId: Int = 0
)