package com.example.alfa_bank_android_app_teacher.data.repository

import android.app.appsearch.StorageInfo
import android.content.Context
import android.graphics.Insets.add
import android.util.Log
import android.widget.Toast
import androidx.annotation.Nullable
import com.example.alfa_bank_android_app_teacher.data.PreferencesImpl
import com.example.alfa_bank_android_app_teacher.data.mapper.TeacherMapper
import com.example.alfa_bank_android_app_teacher.data.network.ApiFactory
import com.example.alfa_bank_android_app_teacher.data.network.modelDto.AuthorizeBodyDto
import com.example.alfa_bank_android_app_teacher.data.network.modelDto.ReasonBody
import com.example.alfa_bank_android_app_teacher.data.network.modelDto.SendDishBodyDto
import com.example.alfa_bank_android_app_teacher.domain.Repository
import com.example.alfa_bank_android_app_teacher.domain.entities.*
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

    override suspend fun loadSchoolClasses(): List<SchoolClass>?  = try{

        apiService.loadSchoolClasses(PreferencesImpl(context).idUser.toString()).map {
            mapper.mapSchoolClassDtoToSchoolClass(it)
        }
    } catch (e:java.lang.Exception){
        null
    }

    override suspend fun loadStudents(grade:String,date: String): List<Student>?  = try{
        Log.d("adfadfsaf",date+" "+grade)
        val students = apiService.loadStudents(date,grade).map {
            mapper.mapStudentDtoToStudent(it)
        }

       students
    } catch (e:java.lang.Exception){
        null
    }

    override suspend fun confirmOrder(date: String, childrenId: String)  = try {
        apiService.confirmOrder(date,childrenId)
    } catch (e:java.lang.Exception){

    }

    override suspend fun cancelOrder(date: String, childrenId: String)  = try {
        apiService.cancelOrder(date,childrenId)
    } catch (e:java.lang.Exception){

    }

    override suspend fun loadChildDishes(date: String, childrenId: String): Int = try{
        val a = apiService.loadChildDishes(date,childrenId)
        Log.d("asdfasf",a.toString())
        1
    }catch (e:java.lang.Exception){
        1
    }

    override suspend fun loadReason(date: String, childrenId: String, reason: String)  = try{
        apiService.loadReason(childrenId,date, ReasonBody(reason))
    }catch (e:Exception){

    }

    override suspend fun getReason(date: String, childrenId: String):String = try{
       apiService.getReason(date,childrenId)[0]
    }catch (e:java.lang.Exception){
        ""
    }

    override suspend fun getChildMenu(date: String, childrenId: String): MenuDish = try {
        val childMenuDto = apiService.getChildMenu(childrenId,date)


        MenuDish(
            breakfast = childMenuDto.breakfast.map{
                Dish(
                    id = 1,
                    name = it.DishName,
                    composition = "",
                    weight = it.DishWeight.replace("г","").toFloat(),
                    cost = it.DishCost.replace("р","").toFloat(),
                    calories = 0f,
                    squirrels = 0f,
                    fat = 0f,
                    carbohydrates = 0f,
                    count = it.Count
                )
            },
            dinner = childMenuDto.dinner.map {
                Dish(
                    id = 1,
                    name = it.DishName,
                    composition = "",
                    weight = it.DishWeight.replace("г","").toFloat(),
                    cost = it.DishCost.replace("р","").toFloat(),
                    calories = 0f,
                    squirrels = 0f,
                    fat = 0f,
                    carbohydrates = 0f,
                    count = it.Count
                )
            },
            snack = childMenuDto.snack.map {
                Dish(
                    id = 1,
                    name = it.DishName,
                    composition = "",
                    weight = it.DishWeight.replace("г","").toFloat(),
                    cost = it.DishCost.replace("р","").toFloat(),
                    calories = 0f,
                    squirrels = 0f,
                    fat = 0f,
                    carbohydrates = 0f,
                    count = it.Count
                )
            }
        )

    }catch (e:Exception){
        Log.d("afasfa",e.message.toString())
        MenuDish()
    }

    override suspend fun getEditChildMenu(
        date: String,
        childrenId: String,
        type: String
    ): EditChildMenu = try{

        val editChildMenu = apiService.getEditChildMenu(childrenId, date, 1)

        EditChildMenu(
            orders = editChildMenu.orders.map {
                Dish(
                    id = it.dishId,
                    name = it.dishName,
                    composition = "",
                    weight = it.dishWeight.replace("г","").replace("мл","").toFloat(),
                    cost = it.dishCost.replace("р","").toFloat(),
                    calories = 0f,
                    squirrels = 0f,
                    fat = 0f,
                    carbohydrates = 0f,
                    count = it.count
                )
            },
            menu = editChildMenu.menu.map {
                Dish(
                    id = it.dishId,
                    name = it.dishName,
                    composition = "",
                    weight =  it.dishWeight.replace("г","").replace("мл","").toFloat(),
                    cost = it.dishCost.replace("р","").toFloat(),
                    calories = 0f,
                    squirrels = 0f,
                    fat = 0f,
                    carbohydrates = 0f,
                    count = 1
                )
            }
        )
    }catch (e:Exception){
        Log.d("adgadsf",e.message.toString())
        EditChildMenu()
    }

    override suspend fun deleteDish(date: String, childrenId: Int, typeMeal: Int, dishId: Int) = try {
        apiService.deleteDish(SendDishBodyDto(date,childrenId,typeMeal,dishId))
    } catch (e:Exception){

    }

    override suspend fun addDish(date: String, childrenId: Int, typeMeal: Int, dishId: Int)= try {
        apiService.addDish(SendDishBodyDto(date,childrenId,typeMeal,dishId))
    } catch (e:Exception){

    }
}