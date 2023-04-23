package com.example.alfa_bank_android_app_teacher.data.network

import com.example.alfa_bank_android_app_teacher.data.network.modelDto.SchoolClassContainerDto
import com.example.alfa_bank_android_app_teacher.data.network.modelDto.StudentContainerDto
import com.example.alfa_bank_android_app_teacher.domain.entities.Student
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("/grades/teacher/{userId}")
    suspend fun loadSchoolClasses(@Path("userId") userId: String): SchoolClassContainerDto

    @GET("/grades/{grade}/childrens")
    suspend fun loadStudents(@Path("grade") grade: String): StudentContainerDto

    @POST("/orders/date/{date}/children/{childrenId}/status/confirm")
    suspend fun confirmOrder(
        @Path("date") date: String = "2022-11-29",
        @Path("childrenId") childrenId: String
    )

    @POST("/orders/date/{date}/children/{childrenId}/status/cancel")
    suspend fun cancelOrder(
        @Path("date") date: String = "2022-11-29",
        @Path("childrenId") childrenId: String
    )
}