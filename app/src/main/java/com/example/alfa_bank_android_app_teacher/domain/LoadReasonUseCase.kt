package com.example.alfa_bank_android_app_teacher.domain

class LoadReasonUseCase(private val repository: Repository) {


    suspend operator fun invoke(data:String,childId:String,reason:String)= repository.loadReason(data,childId,reason)
}