package com.example.alfa_bank_android_app_teacher.ui.classes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alfa_bank_android_app_teacher.domain.entities.SchoolClass

class ClassesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    fun loadSchoolClass(): List<SchoolClass> {
        return listOf(
            SchoolClass(
                "6 А класс",
                "МБОУ СОШ №57"
            ),
            SchoolClass(
                "10 Б класс",
                "МБОУ СОШ №57"
            )
        )
    }
}