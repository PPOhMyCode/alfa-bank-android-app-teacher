package com.example.alfa_bank_android_app_teacher.ui.schoolclass

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.alfa_bank_android_app_teacher.domain.entities.Student

class SchoolClassViewModel(application: Application) : AndroidViewModel(application) {
    fun loadStudents()
    = listOf(Student("Мария", "Синицина",
        isIll = true,
        isEatBreakfast = true,
        isEatDinner = true,
        isEatAfternoonSnack = true
    ),Student("Мария", "Синицина",
        isIll = true,
        isEatBreakfast = true,
        isEatDinner = true,
        isEatAfternoonSnack = true
    ),Student("Мария", "Синицина",
        isIll = true,
        isEatBreakfast = true,
        isEatDinner = true,
        isEatAfternoonSnack = true
    ),Student("Мария", "Синицина",
        isIll = true,
        isEatBreakfast = true,
        isEatDinner = true,
        isEatAfternoonSnack = true
    ),Student("Мария", "Синицина",
        isIll = true,
        isEatBreakfast = true,
        isEatDinner = true,
        isEatAfternoonSnack = true
    ),Student("Мария", "Синицина",
        isIll = true,
        isEatBreakfast = true,
        isEatDinner = true,
        isEatAfternoonSnack = true
    ))
}