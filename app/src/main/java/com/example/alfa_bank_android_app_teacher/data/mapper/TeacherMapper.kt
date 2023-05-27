package com.example.alfa_bank_android_app_teacher.data.mapper

import android.util.Log
import com.example.alfa_bank_android_app_teacher.data.network.modelDto.ParentDto
import com.example.alfa_bank_android_app_teacher.data.network.modelDto.SchoolClassDto
import com.example.alfa_bank_android_app_teacher.data.network.modelDto.StudentDto
import com.example.alfa_bank_android_app_teacher.data.network.modelDto.UserInformation
import com.example.alfa_bank_android_app_teacher.domain.entities.SchoolClass
import com.example.alfa_bank_android_app_teacher.domain.entities.Student
import com.example.alfa_bank_android_app_teacher.domain.entities.User
import com.google.gson.Gson

class TeacherMapper {

    fun mapParentDtoToParent(parentDto: ParentDto): User {
        val testModel = Gson().fromJson(parentDto.user, UserInformation::class.java)
        return User(testModel.FirstName, testModel.SecondName, testModel.UserId, parentDto.url)
    }

    fun mapSchoolClassDtoToSchoolClass(schoolClassDto: SchoolClassDto): SchoolClass {
        return SchoolClass(schoolClassDto.GradeId.toString(), schoolClassDto.Name, "МБОУ СОШ №57")
    }

    fun mapStudentDtoToStudent(studentDto: StudentDto): Student {
        val isEat = true
        Log.d("adfasdf",studentDto.Tags.toString())
        return Student(
            studentDto.ChildrenId.toString(),
            studentDto.Name,
            "",
            isIll = false,
            isEatBreakfast = studentDto.Tags.contains("Завтрак"),
            isEatDinner = studentDto.Tags.contains("Обед"),
            isEatAfternoonSnack = studentDto.Tags.contains("Полдник"),
            isChecked = studentDto.Presence,
            isNotEat = studentDto.Tags.contains("Нет заказа")
        )
    }
}