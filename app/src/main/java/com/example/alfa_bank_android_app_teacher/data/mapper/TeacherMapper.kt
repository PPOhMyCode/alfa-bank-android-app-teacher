package com.example.alfa_bank_android_app_teacher.data.mapper

import com.example.alfa_bank_android_app_teacher.data.network.modelDto.ParentDto
import com.example.alfa_bank_android_app_teacher.data.network.modelDto.SchoolClassDto
import com.example.alfa_bank_android_app_teacher.data.network.modelDto.StudentDto
import com.example.alfa_bank_android_app_teacher.data.network.modelDto.UserInformation
import com.example.alfa_bank_android_app_teacher.domain.entities.SchoolClass
import com.example.alfa_bank_android_app_teacher.domain.entities.Student
import com.example.alfa_bank_android_app_teacher.domain.entities.User
import com.google.gson.Gson
import kotlin.random.Random

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
        return Student(
            studentDto.ChildrenId.toString(),
            studentDto.FirstName,
            studentDto.SecondName,
            isIll = false,
            isEatBreakfast = isEat,
            isEatDinner = isEat,
            isEatAfternoonSnack = isEat,
            isChecked = false,
            isNotEat = !isEat
        )
    }
}