package com.example.alfa_bank_android_app_teacher.domain

class LoadSchoolClassesUseCase(private val repository: Repository) {

    suspend operator fun  invoke()= repository.loadSchoolClasses()
}