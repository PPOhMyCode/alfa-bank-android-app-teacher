package com.example.alfa_bank_android_app_teacher.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Student(
    var id:String,
    var firstName: String,
    var lastName:String,
    var isIll: Boolean,
    var isEatBreakfast:Boolean,
    var isEatDinner:Boolean,
    var isEatAfternoonSnack:Boolean,
    var isChecked:Boolean,
    var isNotEat:Boolean,
) : Parcelable