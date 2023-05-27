package com.example.alfa_bank_android_app_teacher.domain

import com.example.alfa_bank_android_app_teacher.domain.entities.MenuDish

class GetChildMenuUseCase(private val repository: Repository) {

    suspend operator fun invoke(date: String, childId: String): MenuDish =
        repository.getChildMenu(date, childId)
}