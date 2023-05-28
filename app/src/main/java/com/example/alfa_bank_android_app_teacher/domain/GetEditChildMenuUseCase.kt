package com.example.alfa_bank_android_app_teacher.domain

import com.example.alfa_bank_android_app_teacher.domain.entities.EditChildMenu

class GetEditChildMenuUseCase(private val repository: Repository) {

    suspend operator fun invoke(date:String, childrenId: String,type:String) :EditChildMenu
    = repository.getEditChildMenu(date, childrenId,type)
}