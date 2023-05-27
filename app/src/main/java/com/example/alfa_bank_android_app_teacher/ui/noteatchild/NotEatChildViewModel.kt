package com.example.alfa_bank_android_app_teacher.ui.noteatchild

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.alfa_bank_android_app_teacher.data.repository.RepositoryImpl
import com.example.alfa_bank_android_app_teacher.domain.GetReasonUseCase
import com.example.alfa_bank_android_app_teacher.domain.LoadReasonUseCase
import com.example.alfa_bank_android_app_teacher.domain.LoadSchoolClassesUseCase
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class NotEatChildViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = RepositoryImpl(application.applicationContext)
    private val loadReasonUseCase = LoadReasonUseCase(repository)
    private val getReasonUseCase = GetReasonUseCase(repository)

    var  text = MutableLiveData<String>()

    private var date =  SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(Date()).split(" ")[0].replace("/","-").replace("-5","-05").replace("-6","-06").split("-")
    private val newDate = date[2]+"-"+date[1]+"-"+date[0]


    fun loadReason(childId:String, reason:String){
        viewModelScope.launch {
            loadReasonUseCase.invoke(data = "2023-05-26", childId = childId, reason = reason)//TODO:поменять обратно дату
        }
    }

    fun getReason(childId:String){
        viewModelScope.launch {
            val a = getReasonUseCase.invoke(date = childId, childrenId = "2023-05-26") //TODO:поменять обратно дату
           text.value = a
        }
    }
}