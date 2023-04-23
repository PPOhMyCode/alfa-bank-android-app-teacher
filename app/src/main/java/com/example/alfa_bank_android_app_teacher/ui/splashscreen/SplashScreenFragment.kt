package com.example.alfa_bank_android_app_teacher.ui.splashscreen

import android.annotation.SuppressLint
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.alfa_bank_android_app_parent_2.ui.splashscreen.SplashScreenViewModel
import com.example.alfa_bank_android_app_teacher.ui.authentication.AuthenticationFragment
import com.example.alfa_bank_android_app_teacher.databinding.FragmentSplashScreenBinding
import com.example.alfa_bank_android_app_teacher.ui.authorization.AuthorizationFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashScreenFragment : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this)[SplashScreenViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val _binding = FragmentSplashScreenBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        lifecycleScope.launch {
            delay(1000)
            when (viewModel.preferences.user) {
                null -> launchAuthorizationFragment()
                else -> launchAuthenticationFragment()
           }
        }
    }


    private fun launchAuthenticationFragment() {
        startActivity(AuthenticationFragment.getAuthenticationIntent(this))
        finish()
    }

    private fun launchAuthorizationFragment() {
        startActivity(Intent(this,AuthorizationFragment::class.java))
        finish()
    }
}