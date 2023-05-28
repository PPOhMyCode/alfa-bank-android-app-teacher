package com.example.alfa_bank_android_app_teacher.data.network.modelDto

import com.google.gson.annotations.SerializedName

data class EditChildMenuContainerDto(
    @SerializedName("Menu")
    val menu: List<Menu>,
    @SerializedName("Orders")
    val orders: List<Order>
)