package com.example.alfa_bank_android_app_teacher.ui.child

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.alfa_bank_android_app_teacher.data.repository.RepositoryImpl
import com.example.alfa_bank_android_app_teacher.domain.*
import com.example.alfa_bank_android_app_teacher.domain.entities.Dish
import com.example.alfa_bank_android_app_teacher.domain.entities.SchoolClass
import kotlinx.coroutines.launch

class ChildViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RepositoryImpl(application.applicationContext)
    private val getChildMenuUseCase = GetChildMenuUseCase(repository)

    var breakfastDishes = MutableLiveData<List<Dish>>()
    var dinnerDishes = MutableLiveData<List<Dish>>()
    var snackDishes = MutableLiveData<List<Dish>>()

    fun loadChildDish(){
        viewModelScope.launch {
            val menu = getChildMenuUseCase.invoke("2023-05-26", "1") //TODO: Поменять
            breakfastDishes.value = menu.breakfast
            dinnerDishes.value = menu.dinner
            snackDishes.value = menu.snack
        }
    }
}