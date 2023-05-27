package com.example.alfa_bank_android_app_teacher.data.network

import com.example.alfa_bank_android_app_teacher.data.network.modelDto.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("/grades/teacher/{userId}")
    suspend fun loadSchoolClasses(@Path("userId") userId: String): SchoolClassContainerDto

    @GET("/orders/date/{date}/gradeid/{grade}")
    suspend fun loadStudents(@Path("date") date:String, @Path("grade") grade: String):StudentContainerDto

    @POST("/orders/date/{date}/children/{childrenId}/status/confirm")
    suspend fun confirmOrder(
        @Path("date") date: String,
        @Path("childrenId") childrenId: String
    )

    @POST("/orders/date/{date}/children/{childrenId}/status/cancel")
    suspend fun cancelOrder(
        @Path("date") date: String,
        @Path("childrenId") childrenId: String
    )

    @GET("/orders/date/{date}/children/{childrenId}")
    suspend fun loadChildDishes(
        @Path("date") date: String,
        @Path("childrenId") childrenId: String
    ): ChildDishesContainerDto

    @POST("/refusal/children/{childrenId}/date/{date}")
    suspend fun loadReason(@Path("childrenId")childrenId:String,
                           @Path("date") date: String,
                            @Body body: ReasonBody)

    @GET("/refusal/children/{childrenId}/date/{date}")
    suspend fun getReason(@Path("childrenId")childrenId:String,
                          @Path("date") date: String):NotEatDto

    @GET("/orders/date/{date}/children/{childrenId}")
    suspend fun getChildMenu(@Path("childrenId")childrenId:String,
                             @Path("date") date: String):ChildMenuContainerDto
}