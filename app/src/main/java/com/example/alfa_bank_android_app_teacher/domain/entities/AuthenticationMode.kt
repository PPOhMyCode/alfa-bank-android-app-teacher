package com.example.alfa_bank_android_app_teacher.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class AuthenticationMode:Parcelable {
    INPUT_FIRST_TIME_PASSWORD_MODE,
    INPUT_SECOND_TIME_PASSWORD_MODE,
    AUTHENTICATION_MODE
}