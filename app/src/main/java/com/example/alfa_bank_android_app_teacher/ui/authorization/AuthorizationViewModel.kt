package com.example.alfa_bank_android_app_teacher.ui.authorization

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alfa_bank_android_app_teacher.data.PreferencesImpl
import com.example.alfa_bank_android_app_teacher.data.preferences.PreferencesUserImpl
import com.example.alfa_bank_android_app_teacher.data.repository.RepositoryImpl
import com.example.alfa_bank_android_app_teacher.domain.AuthorizeParentUseCase
import com.example.alfa_bank_android_app_teacher.domain.entities.Parent
import kotlinx.coroutines.launch

class AuthorizationViewModel(application: Application) : AndroidViewModel(application) {
    val preferences = PreferencesImpl(application.applicationContext)
    val preferencesUserImpl = PreferencesUserImpl(application.applicationContext)
    var parent: MutableLiveData<String> = MutableLiveData<String>()
    var isUserStartLog: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)

    private val repositoryImpl = RepositoryImpl(application.applicationContext)
    private val authorizeParentUseCase = AuthorizeParentUseCase(repositoryImpl)


    fun authorization(login: String, password: String) {
        isUserStartLog.value = true
        viewModelScope.launch {
            authorizeParentUseCase.invoke(login, password)?.let {
                preferences.idUser = it.id
                isUserStartLog.value = false
                parent.value = "A"
                preferencesUserImpl.user= Parent(it.firstName,it.lastName,it.id,it.url)
            }
            parent.value = "Б"
            //parent.value?.let { preferences.user = it }
        }
    }
}