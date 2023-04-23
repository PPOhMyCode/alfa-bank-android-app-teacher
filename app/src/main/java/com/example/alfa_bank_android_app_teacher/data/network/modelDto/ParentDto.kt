package com.example.alfa_bank_android_app_teacher.data.network.modelDto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ParentDto(
    @SerializedName("url")
    @Expose
    val url: String,

    @SerializedName("user")
    @Expose
    val user: String
)