package com.example.alfa_bank_android_app_teacher.domain

class LoadStudentsUseCase(private val repository: Repository) {

    suspend operator fun invoke(grade:String) = repository.loadStudents(grade)
}