package com.example.alfa_bank_android_app_teacher.data.network.modelDto

import com.google.gson.annotations.SerializedName

data class StudentDto(
    @SerializedName("ChildrenId")
    val ChildrenId: Int,
    @SerializedName("Name")
    val Name: String,
    @SerializedName("Presence")
    val Presence: Boolean,
    @SerializedName("Tags")
    val Tags: List<String>
)