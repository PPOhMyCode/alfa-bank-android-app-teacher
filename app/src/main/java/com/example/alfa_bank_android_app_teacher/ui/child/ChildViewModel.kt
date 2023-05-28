package com.example.alfa_bank_android_app_teacher.ui.child

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.alfa_bank_android_app_teacher.data.preferences.PreferencesUserImpl
import com.example.alfa_bank_android_app_teacher.data.repository.RepositoryImpl
import com.example.alfa_bank_android_app_teacher.domain.*
import com.example.alfa_bank_android_app_teacher.domain.entities.Dish
import com.example.alfa_bank_android_app_teacher.domain.entities.SchoolClass
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class ChildViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RepositoryImpl(application.applicationContext)
    private val getChildMenuUseCase = GetChildMenuUseCase(repository)

    var breakfastDishes = MutableLiveData<List<Dish>>()
    var dinnerDishes = MutableLiveData<List<Dish>>()
    var snackDishes = MutableLiveData<List<Dish>>()

    private var date =  SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(Date()).split(" ")[0].replace("/","-").replace("-5","-05").replace("-6","-06").split("-")
    private val newDate = date[2]+"-"+date[1]+"-"+date[0]

    val childId = PreferencesUserImpl(application).userChild?.id


    fun loadChildDish(){
        Log.d("asdfasdf",childId.toString())
        viewModelScope.launch {
            val menu = getChildMenuUseCase.invoke("2023-05-26", "1") //TODO: Поменять
            breakfastDishes.value = menu.breakfast
            dinnerDishes.value = menu.dinner
            snackDishes.value = menu.snack
        }
    }
}