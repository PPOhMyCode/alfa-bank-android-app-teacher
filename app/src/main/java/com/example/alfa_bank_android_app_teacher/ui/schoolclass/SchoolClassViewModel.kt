package com.example.alfa_bank_android_app_teacher.ui.schoolclass

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.alfa_bank_android_app_teacher.data.repository.RepositoryImpl
import com.example.alfa_bank_android_app_teacher.domain.CancelOrderUseCase
import com.example.alfa_bank_android_app_teacher.domain.ConfirmOrderUseCase
import com.example.alfa_bank_android_app_teacher.domain.LoadSchoolClassesUseCase
import com.example.alfa_bank_android_app_teacher.domain.LoadStudentsUseCase
import com.example.alfa_bank_android_app_teacher.domain.entities.SchoolClass
import com.example.alfa_bank_android_app_teacher.domain.entities.Student
import kotlinx.coroutines.launch
import java.lang.Exception

class SchoolClassViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RepositoryImpl(application.applicationContext)
    private val loadStudentsUseCase = LoadStudentsUseCase(repository)
    private val cancelOrderUseCase = CancelOrderUseCase(repository)
    private val confirmOrderUseCase = ConfirmOrderUseCase(repository)

    var students = MutableLiveData<List<Student>?>()
    var status = MutableLiveData<String>()

    private var eatStudents = mutableListOf<Student>()

    fun loadStudents(grade: String) {
        viewModelScope.launch {
            students.value = loadStudentsUseCase(grade)
        }
    }

    fun addStudent(student: Student){
        eatStudents.add(student)
    }

    fun removeStudent(student: Student){
        eatStudents.removeIf {
            it.id == student.id
        }
    }

    fun addAllStudent(){

        eatStudents = mutableListOf()

        students.value?.let {
            eatStudents.addAll(
                it
            )
        }

        students.value = students.value?.map {
            it.copy(
                isChecked = true
            )
        }
    }

    fun removeAllStudent(){
        eatStudents = mutableListOf()

        students.value = students.value?.map {
            it.copy(
                isChecked = false
            )
        }
    }

    fun makeOrder(students: List<Student>){
        //TODO("Переделать на eatStudent")
        try {
            viewModelScope.launch {
                for (student in students) {
                    if (student.isChecked)
                        confirmOrderUseCase.invoke("2022-12-19", student.id)
                    else
                        cancelOrderUseCase.invoke("2022-12-19", student.id)
                }
            }
            status.value="Заявки. Отправлено ${eatStudents.size}"
        } catch (e: Exception) {
            status.value="Ошибка"
        }
    }
}