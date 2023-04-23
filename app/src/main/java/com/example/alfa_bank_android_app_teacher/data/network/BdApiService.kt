package com.example.alfa_bank_android_app_teacher.data.network


import com.example.alfa_bank_android_app_teacher.data.network.modelDto.AuthorizeBodyDto
import com.example.alfa_bank_android_app_teacher.data.network.modelDto.ParentDto
import retrofit2.http.*


interface BdApiService {
    @POST("/authorization")
    suspend fun authorizeParent(@Body authorizeBody: AuthorizeBodyDto): ParentDto

}