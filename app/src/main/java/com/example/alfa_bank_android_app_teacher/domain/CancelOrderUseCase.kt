package com.example.alfa_bank_android_app_teacher.domain

class CancelOrderUseCase(private val repository: Repository) {

    suspend operator fun invoke(date: String, childId: String) =
        repository.confirmOrder(date, childId)
}