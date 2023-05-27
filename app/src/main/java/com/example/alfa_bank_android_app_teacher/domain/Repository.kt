package com.example.alfa_bank_android_app_teacher.domain

import androidx.annotation.Nullable
import com.example.alfa_bank_android_app_teacher.domain.entities.MenuDish
import com.example.alfa_bank_android_app_teacher.domain.entities.User
import com.example.alfa_bank_android_app_teacher.domain.entities.SchoolClass
import com.example.alfa_bank_android_app_teacher.domain.entities.Student

interface Repository {

    suspend fun authorizeUser(login: String, password: String): User?

    suspend fun loadSchoolClasses():List<SchoolClass>?

    suspend fun loadStudents(grade:String,date: String):List<Student>?

    suspend fun confirmOrder(date: String,childrenId:String)

    suspend fun cancelOrder(date: String,childrenId:String)

    suspend fun loadChildDishes(date:String, childrenId: String):Int

    suspend fun loadReason(date:String, childrenId: String,reason:String)

    suspend fun getReason(date:String, childrenId: String):String

    suspend fun getChildMenu(date:String, childrenId: String):MenuDish
}