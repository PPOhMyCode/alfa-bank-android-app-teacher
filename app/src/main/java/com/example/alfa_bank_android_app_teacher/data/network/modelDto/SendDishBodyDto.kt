package com.example.alfa_bank_android_app_teacher.data.network.modelDto

import com.google.gson.annotations.SerializedName

data class SendDishBodyDto (
    @SerializedName("Date")
    val Date: String,

    @SerializedName("ChildrenId")
    val ChildrenId: Int,

    @SerializedName("TypeMealId")
    val TypeMealId: Int,

    @SerializedName("DishId")
    val DishId: Int,
)