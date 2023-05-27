package com.example.alfa_bank_android_app_teacher.data.network.modelDto

import com.google.gson.annotations.SerializedName

data class ChildMenuContainerDto(
    @SerializedName("Завтрак")
    val breakfast: List<ChildMenuItemDto> = listOf(),
    @SerializedName("Обед")
    val dinner: List<ChildMenuItemDto> = listOf(),
    @SerializedName("Ужин")
    val snack: List<ChildMenuItemDto> = listOf()
)