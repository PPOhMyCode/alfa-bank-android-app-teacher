package com.example.alfa_bank_android_app_teacher.data.repository

import android.app.appsearch.StorageInfo
import android.content.Context
import android.widget.Toast
import com.example.alfa_bank_android_app_teacher.data.PreferencesImpl
import com.example.alfa_bank_android_app_teacher.data.mapper.TeacherMapper
import com.example.alfa_bank_android_app_teacher.data.network.ApiFactory
import com.example.alfa_bank_android_app_teacher.data.network.modelDto.AuthorizeBodyDto
import com.example.alfa_bank_android_app_teacher.domain.Repository
import com.example.alfa_bank_android_app_teacher.domain.entities.SchoolClass
import com.example.alfa_bank_android_app_teacher.domain.entities.Student
import com.example.alfa_bank_android_app_teacher.domain.entities.User
import retrofit2.HttpException

class RepositoryImpl(var context:Context) : Repository {

    private val bdApiService = ApiFactory.getBdApiService()
    private val apiService = ApiFactory.getApiService()
    private val mapper = TeacherMapper()


    override suspend fun authorizeUser(login: String, password: String): User?= try {
        val parentDto = bdApiService.authorizeParent(AuthorizeBodyDto(login, password))
        mapper.mapParentDtoToParent(parentDto)
    } catch (
        e: HttpException
    ) {
        if (e.code() == 300)
            Toast.makeText(context, "Введет неверный логин или пароль", Toast.LENGTH_SHORT).show()
        else if (e.code() == 502)
            Toast.makeText(context, "Ошибка", Toast.LENGTH_SHORT).show()
        null
    }

    override suspend fun loadSchoolClasses(): List<SchoolClass>? {
        val id = PreferencesImpl(context).idUser
        return apiService.loadSchoolClasses(id.toString()).map {
            mapper.mapSchoolClassDtoToSchoolClass(it)
        }
    }

    override suspend fun loadStudents(grade:String): List<Student>? {
        return apiService.loadStudents(grade).map {
            mapper.mapStudentDtoToStudent(it)
        }
    }

    override suspend fun confirmOrder(date: String, childrenId: String) {
        apiService.confirmOrder(date,childrenId)
    }

    override suspend fun cancelOrder(date: String, childrenId: String) {
        apiService.cancelOrder(date,childrenId)
    }
}