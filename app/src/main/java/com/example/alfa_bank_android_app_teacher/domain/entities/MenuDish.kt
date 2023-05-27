package com.example.alfa_bank_android_app_teacher.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MenuDish(
    var breakfast:List<Dish> = listOf(),
    var dinner:List<Dish> = listOf(),
    var snack:List<Dish> = listOf()
):Parcelable
