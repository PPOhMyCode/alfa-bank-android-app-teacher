package com.example.alfa_bank_android_app_teacher.data.network.modelDto

import com.google.gson.annotations.SerializedName

data class SchoolClassDto(
    @SerializedName("GradeId")
    val GradeId: Int,

    @SerializedName("Name")
    val Name: String,

    @SerializedName("TeacherId")
    val TeacherId: Int
)