package com.example.alfa_bank_android_app_teacher.domain

import androidx.annotation.Nullable

class LoadChildDishesUseCase(private val repository: Repository) {
    suspend operator fun invoke(date:String,childId:String):Int= repository.loadChildDishes(date,childId)
}