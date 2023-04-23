package com.example.alfa_bank_android_app_teacher.ui.authorization

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.alfa_bank_android_app_teacher.MainActivity
import com.example.alfa_bank_android_app_teacher.databinding.FragmentAuthorizationBinding
import com.example.alfa_bank_android_app_teacher.ui.authentication.AuthenticationFragment


class AuthorizationFragment : AppCompatActivity() {

    companion object {
        fun newInstance() = AuthorizationFragment()
    }

    private var _binding: FragmentAuthorizationBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AuthorizationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = FragmentAuthorizationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[AuthorizationViewModel::class.java]
        initializeButtonClick()
        initializeObserve()
    }


    @SuppressLint("SuspiciousIndentation")
    private fun initializeObserve() {
        viewModel.parent.observe(this) {
            binding.progressBar.visibility= View.GONE
            binding.backgroundColorFrameLayout.visibility = View.GONE
                it?.let {
                    if(it=="A"){

                        startActivity(AuthenticationFragment.getFirstTimeAuthentication(this))
                        finish()
                    }
                //findNavController().navigate(
                //    AuthorizationFragmentDirections.actionAuthorizationToAuthentication(
                //        AuthenticationMode.INPUT_FIRST_TIME_PASSWORD_MODE,
                //        null
                //    )
                //)
            }
       }
    }

    private fun initializeButtonClick() {
        binding.buttonAuthorization.setOnClickListener {
            val login = binding.loginTextInputEditText.text.toString()
            val password = binding.passwordTextInputEditText.text.toString()
            if (login == "")
                Toast.makeText(this, "Введите логин", Toast.LENGTH_SHORT).show()
            else if (password == "")
                Toast.makeText(this, "Введите пароль", Toast.LENGTH_SHORT).show()
            else if (viewModel.isUserStartLog.value == false) {
                binding.progressBar.visibility= View.VISIBLE
                binding.backgroundColorFrameLayout.visibility = View.VISIBLE
                binding.loginTextInputEditText.text?.clear()
                binding.passwordTextInputEditText.text?.clear()
                viewModel.authorization(login, password)
            }
        }
    }

}