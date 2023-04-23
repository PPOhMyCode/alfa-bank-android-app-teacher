package com.example.alfa_bank_android_app_teacher.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Child(
    var id:Int,
    var firstName: String,
    var lastName:String,
    var schoolClass: String,
    var school: String,
    var account: Float
):Parcelable