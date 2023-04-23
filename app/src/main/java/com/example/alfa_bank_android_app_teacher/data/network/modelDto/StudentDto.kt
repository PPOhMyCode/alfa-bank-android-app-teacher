package com.example.alfa_bank_android_app_teacher.data.network.modelDto

import com.google.gson.annotations.SerializedName

data class StudentDto(
    @SerializedName("ChildrenId")
    val ChildrenId: Int,

    @SerializedName("FirstName")
    val FirstName: String,

    @SerializedName("GradeId")
    val GradeId: Int,

    @SerializedName("ParentID")
    val ParentID: Int,

    @SerializedName("Patronymic")
    val Patronymic: String,

    @SerializedName("SecondName")
    val SecondName: String
)