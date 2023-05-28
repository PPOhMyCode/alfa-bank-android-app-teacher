package com.example.alfa_bank_android_app_teacher.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EditChildMenu(
    var orders:List<Dish> = listOf(),
    var menu:List<Dish> = listOf(),
):Parcelable
