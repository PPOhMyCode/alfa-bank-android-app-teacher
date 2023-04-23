package com.example.alfa_bank_android_app_teacher.data.network.modelDto

import com.google.gson.annotations.SerializedName



class AuthorizeBodyDto (
    @SerializedName("Login")
    val login: String,

    @SerializedName("Password")
    val password:String
)
