package com.example.alfa_bank_android_app_teacher.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dish(
    var id:Int,
    var name: String,
    var composition:  String ,
    var weight: Float,
    var cost: Float,
    var calories:Float,
    var squirrels:Float,
    var fat:Float,
    var carbohydrates:Float,
    var count:Int= 0
) : Parcelable