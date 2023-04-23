package com.example.alfa_bank_android_app_teacher.data

import android.content.Context
import android.content.SharedPreferences
import com.example.alfa_bank_android_app_teacher.domain.Preferences

class PreferencesImpl(context: Context): Preferences() {

    private val preferences: SharedPreferences =
        context.getSharedPreferences(
            SHARED_PREFERENCES_USER,
            Context.MODE_PRIVATE
        )

    override var idUser: Int
        get() = preferences.getInt(ID, 0)
        set(value) = preferences.edit().putInt(ID, value).apply()

    companion object {
        private val SHARED_PREFERENCES_USER = "SHARED_PREFERENCES_USER"
        private val ID = "ID"
    }
}