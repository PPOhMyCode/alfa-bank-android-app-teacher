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
import java.text.SimpleDateFormat
import java.util.*

class SchoolClassViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RepositoryImpl(application.applicationContext)
    private val loadStudentsUseCase = LoadStudentsUseCase(repository)
    private val cancelOrderUseCase = CancelOrderUseCase(repository)
    private val confirmOrderUseCase = ConfirmOrderUseCase(repository)

    var students = MutableLiveData<List<Student>?>()
    var status = MutableLiveData<String>()

    private var eatStudents = mutableListOf<Student>()

    private var date =  SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(Date()).split(" ")[0].replace("/","-").replace("-5","-05").replace("-6","-06").split("-")

    private val newDate = date[2]+"-"+date[1]+"-"+date[0]

    fun loadStudents(grade: String) {
        Log.d("afadfadf",newDate )
        viewModelScope.launch {
            students.value = loadStudentsUseCase(grade,"2023-05-26") // TODO: вернуть newDate
        }
    }

    fun addStudent(student: Student){
        Log.d("adfadf",eatStudents.toString())
        eatStudents.add(student)
    }

    fun removeStudent(student: Student){
        Log.d("adfadf",eatStudents.toString())
        eatStudents.removeIf {
            it.id == student.id
        }
    }

    fun addAllStudent(){
        Log.d("adfadf",eatStudents.toString())

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
        Log.d("adfadf",eatStudents.toString())

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
                    if (student.isChecked) {
                        Log.d("adfas",student.firstName + "  confirm")
                        confirmOrderUseCase.invoke(newDate, student.id)
                    }
                    else {
                        Log.d("adfas",student.firstName + "  cancel")
                        cancelOrderUseCase.invoke(newDate, student.id)
                    }
                }
            }
            status.value="Заявки. Отправлено ${eatStudents.size}"
        } catch (e: Exception) {
            status.value="Ошибка"
        }
    }
}