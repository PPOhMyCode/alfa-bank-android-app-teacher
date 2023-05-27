package com.example.alfa_bank_android_app_teacher.data.network.modelDto

import com.google.gson.annotations.SerializedName

data class ChildMenuItemDto(
    @SerializedName("Count")
    val Count: Int,
    @SerializedName("DishCost")
    val DishCost: String,
    @SerializedName("DishName")
    val DishName: String,
    @SerializedName("DishWeight")
    val DishWeight: String
)