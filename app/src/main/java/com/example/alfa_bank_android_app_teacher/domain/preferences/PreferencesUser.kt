package com.example.alfa_bank_android_app_teacher.domain.preferences

import com.example.alfa_bank_android_app_teacher.domain.entities.Child
import com.example.alfa_bank_android_app_teacher.domain.entities.Parent

abstract class PreferencesUser() {
    abstract var user : Parent?
    abstract var userPinCode: String?
    abstract var userChild: Child?

}