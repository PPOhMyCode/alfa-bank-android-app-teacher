package com.example.alfa_bank_android_app_teacher.domain

class AddDishUseCase(private val repository: Repository) {

    suspend operator fun invoke( date:String, childrenId:Int, typeMeal: Int, dishId: Int) =
        repository.addDish(date, childrenId,typeMeal, dishId)
}