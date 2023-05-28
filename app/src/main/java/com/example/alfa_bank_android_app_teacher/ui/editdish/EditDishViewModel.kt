package com.example.alfa_bank_android_app_teacher.ui.editdish

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alfa_bank_android_app_teacher.data.preferences.PreferencesUserImpl
import com.example.alfa_bank_android_app_teacher.data.repository.RepositoryImpl
import com.example.alfa_bank_android_app_teacher.domain.AddDishUseCase
import com.example.alfa_bank_android_app_teacher.domain.DeleteDishUseCase
import com.example.alfa_bank_android_app_teacher.domain.GetEditChildMenuUseCase
import com.example.alfa_bank_android_app_teacher.domain.LoadSchoolClassesUseCase
import com.example.alfa_bank_android_app_teacher.domain.entities.AuthenticationItemsForAdapter
import com.example.alfa_bank_android_app_teacher.domain.entities.Dish
import com.example.alfa_bank_android_app_teacher.ui.adapters.DishListAdapter
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class EditDishViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RepositoryImpl(application.applicationContext)
    private val loadSchoolClassesUseCase = GetEditChildMenuUseCase(repository)
    private val deleteDishUseCase = DeleteDishUseCase(repository)
    private val addDishUseCase  = AddDishUseCase(repository)



    var currentDishes = MutableLiveData<MutableList<Dish>>()
    var menuDishes = MutableLiveData<MutableList<Dish>>()

    private val addedDishes = mutableListOf<Dish>()
    private val removeDishes = mutableListOf<Dish>()


    private var date =  SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(Date()).split(" ")[0].replace("/","-").replace("-5","-05").replace("-6","-06").split("-")
    private val newDate = date[2]+"-"+date[1]+"-"+date[0]

    val childId = PreferencesUserImpl(application).userChild?.id

    fun loadMenu(type:String){
        Log.d("afdasdf",type)
        viewModelScope.launch {
            val result =  loadSchoolClassesUseCase.invoke("2023-05-29","3","1")
            currentDishes.value = result.orders.toMutableList()
            menuDishes.value = result.menu.toMutableList()
        }

    }

    fun addMenuDish(dish: Dish, menuAdapter: DishListAdapter,currentAdapter: DishListAdapter){
        menuDishes.value?.removeIf {
            it.id ==dish.id
        }
        menuDishes.value?.let{
            menuAdapter.dishes = it
        }
        currentDishes.value?.add(
            dish
        )
        currentDishes.value?.let{
            currentAdapter.dishes = it
        }
        addedDishes.add(dish)

        menuAdapter.notifyDataSetChanged()
        currentAdapter.notifyDataSetChanged()

        Log.d("dfasdfasd","currentDishes"+ currentDishes.value.toString())
        Log.d("dfasdfasd","menuDishes"+ menuDishes.value.toString())
    }

    fun addCurrentDish(dish: Dish, menuAdapter: DishListAdapter,currentAdapter: DishListAdapter){
        val dish = currentDishes.value?.find { it-> it.id ==dish.id }
        dish?.let {
            if(dish.count<3) {
                dish.count += 1
                addedDishes.add(dish)
            }
        }
        Log.d("dfasdfasd","currentDishes"+ currentDishes.value.toString())
        Log.d("dfasdfasd","menuDishes"+ menuDishes.value.toString())


        menuAdapter.notifyDataSetChanged()
        currentAdapter.notifyDataSetChanged()
    }

    fun removeDish(dish: Dish, menuAdapter: DishListAdapter,currentAdapter: DishListAdapter){


        val dish = currentDishes.value?.find { it-> it.id ==dish.id }
        dish?.let {
            if (dish.count == 1) {
                currentDishes.value?.removeIf { it2->it2.id ==dish.id }
                menuDishes.value?.add(dish)

            }else{
                dish.count-=1
            }
            removeDishes.add(dish)
        }

        Log.d("dfasdfasd","currentDishes"+ currentDishes.value.toString())
        Log.d("dfasdfasd","menuDishes"+ menuDishes.value.toString())

        menuAdapter.notifyDataSetChanged()
        currentAdapter.notifyDataSetChanged()
    }

    fun saveButton(type:String){
        viewModelScope.launch {
            for (dish in addedDishes) {
                Log.d("asdfasdf","addedDishes")
                addDishUseCase.invoke(newDate, childId ?: 0, type.toInt(), dishId = dish.id)
            }

            for (dish in removeDishes) {
                Log.d("asdfasdf","removeDish")
                deleteDishUseCase.invoke(newDate,childId?:0,type.toInt(), dishId = dish.id)
            }
        }
    }
}