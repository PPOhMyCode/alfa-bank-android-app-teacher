package com.example.alfa_bank_android_app_teacher.data.network.modelDto

import com.google.gson.annotations.SerializedName

data class Menu(
    @SerializedName("Count")
    val count: Int,
    @SerializedName("DishCost")
    val dishCost: String,
    @SerializedName("DishId")
    val dishId: Int,
    @SerializedName("DishName")
    val dishName: String,
    @SerializedName("DishWeight")
    val dishWeight: String
)