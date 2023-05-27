package com.example.alfa_bank_android_app_teacher.data.network.modelDto

import com.google.gson.annotations.SerializedName

data class ChildDishesItem(
    @SerializedName("dishes")
    val dishes: List<String>,
    @SerializedName("name")
    val name: String
)