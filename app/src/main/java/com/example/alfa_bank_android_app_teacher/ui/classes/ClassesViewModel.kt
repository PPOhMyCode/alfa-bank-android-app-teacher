package com.example.alfa_bank_android_app_teacher.ui.classes

import android.app.Application
import androidx.lifecycle.*
import com.example.alfa_bank_android_app_teacher.data.repository.RepositoryImpl
import com.example.alfa_bank_android_app_teacher.domain.LoadSchoolClassesUseCase
import com.example.alfa_bank_android_app_teacher.domain.entities.SchoolClass
import kotlinx.coroutines.launch

class ClassesViewModel(application: Application) : AndroidViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private val repository = RepositoryImpl(application.applicationContext)
    private val loadSchoolClassesUseCase = LoadSchoolClassesUseCase(repository)

    var schoolClasses = MutableLiveData<List<SchoolClass>?>()


    fun loadSchoolClass() {
        viewModelScope.launch {
            schoolClasses.value = loadSchoolClassesUseCase()
        }
    }
}