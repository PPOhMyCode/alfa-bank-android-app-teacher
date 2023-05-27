package com.example.alfa_bank_android_app_teacher.domain

class GetReasonUseCase(private val repository: Repository) {

    suspend operator fun invoke(date:String, childrenId: String) :String= repository.getReason(date, childrenId)
}