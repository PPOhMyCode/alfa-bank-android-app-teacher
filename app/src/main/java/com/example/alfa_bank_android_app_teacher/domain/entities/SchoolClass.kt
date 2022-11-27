package com.example.alfa_bank_android_app_teacher.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SchoolClass(
    var schoolClass: String,
    var school: String,
):Parcelable