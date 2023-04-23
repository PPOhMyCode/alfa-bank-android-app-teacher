package com.example.alfa_bank_android_app_teacher.domain


import com.example.alfa_bank_android_app_teacher.domain.entities.User

class AuthorizeParentUseCase(private val repository: Repository) {

    suspend operator fun invoke(login: String, password: String): User? =
        repository.authorizeUser(login, password)
}