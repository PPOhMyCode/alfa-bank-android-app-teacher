package com.example.alfa_bank_android_app_teacher.ui.authentication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.alfa_bank_android_app_parent_2.ui.authentication.AuthenticationViewModel
import com.example.alfa_bank_android_app_teacher.MainActivity
import com.example.alfa_bank_android_app_teacher.R
import com.example.alfa_bank_android_app_teacher.databinding.FragmentAuthenticationBinding
import com.example.alfa_bank_android_app_teacher.domain.entities.AuthenticationItemsForAdapter
import com.example.alfa_bank_android_app_teacher.domain.entities.AuthenticationMode
import com.example.alfa_bank_android_app_teacher.domain.entities.PinClass
import com.example.alfa_bank_android_app_teacher.ui.adapters.AuthenticationCardAdapter
import java.util.concurrent.Executor


class AuthenticationFragment : AppCompatActivity() {

    private lateinit var pinClass: PinClass

    //private val args by navArgs<AuthenticationFragmentArgs>()


    private lateinit var viewModel : AuthenticationViewModel

    private lateinit var _binding: FragmentAuthenticationBinding
    //private val binding: FragmentAuthenticationBinding
    //    get() = _binding ?: throw RuntimeException("FragmentAuthenticationBinding == null")

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo
    private lateinit var authenticationCardAdapter: AuthenticationCardAdapter

    private lateinit var authenticationMode:String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = FragmentAuthenticationBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        pinClass = PinClass(
            listOf(
                _binding.buttonUncheked1,
                _binding.buttonUncheked2,
                _binding.buttonUncheked3,
                _binding.buttonUncheked4
            )
        )
        authenticationMode= intent.getStringExtra("Mode").toString()

        viewModel = AuthenticationViewModel(this,pinClass,authenticationMode)



        initializeMode()
        initializeBiometricAuthentication()
        initializeRecyclerView()
        viewModel.pinClass.adapter = {
            changeAuthenticationDataAdapter()
        }
    }

    override fun onStart() {
        super.onStart()
    }

    private fun initializeMode() {

        when (authenticationMode) {
            "INPUT_FIRST_TIME_PASSWORD_MODE" -> {
                startFirstTimeMode()
            }
            "INPUT_SECOND_TIME_PASSWORD_MODE" -> {
                startSecondTimeMode()
            }
            "AUTHENTICATION_MODE"  -> {
                startAuthenticationMode()
            }

        }
    }

    private fun startFirstTimeMode() {
        _binding.text.text = getString(R.string.input_pin)
        viewModel.funAfterPinWasEntered = {
            it?.let {it2->
                startActivity( getSecondTimeAuthentication(this, it2) )
                finish()
            }
            //findNavController().navigate(
            //    AuthenticationFragmentDirections.actionAuthenticationSelf(
            //        AuthenticationMode.INPUT_SECOND_TIME_PASSWORD_MODE,
            //        it
            //    )
            //
            //)
            //val intent = it?.let { it1 -> newIntentInputSecondTimePinCode(this, it1) }
            //this.startActivity(intent)
            //finish()
        }
    }

    private fun startSecondTimeMode() {
        _binding.text.text = getString(R.string.input_second_time_pin)
        viewModel.funAfterPinWasEntered = {
            if (intent.getStringExtra("Pin").toString() == it) {
                it.let { pinCode ->
                    viewModel.userPreferences.userPinCode = pinCode
                }

                viewModel.pinClass.removePin()
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                for (circle in viewModel.pinClass.circles) {
                    animateIncorrectPasswordView(circle, 6, 140f, 20f)
                }
            }
        }
    }

    private fun animateIncorrectPasswordView(
        view: View,
        countAction: Int,
        value: Float,
        stepValue: Float,
        nowDeep: Int = 0
    ) {
        if (nowDeep == countAction) {
            ViewCompat.animate(view)
                .translationX(0f)
                .setDuration(100L)
                .withEndAction {
                    viewModel.pinClass.removePin()
                }
                .start()
            return
        }
        ViewCompat.animate(view)
            .translationX(value)
            .setDuration(100L)
            .withEndAction {
                animateIncorrectPasswordView(
                    view,
                    countAction,
                    (value - stepValue) * -1,
                    stepValue * -1,
                    nowDeep + 1
                )
            }
            .start()
    }

    private fun animateCorrectPasswordView(
        views: List<ImageView>,
        scaleX: Float,
        scaleY: Float,
        alpha: Float,
        duration: Long,
        deep: Int,
        count: Int
    ) {
        if (views.size < 4)
            return
        if (deep - 1 == count) {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        ViewCompat.animate(views[0])
            .scaleY(scaleY)
            .scaleX(scaleX)
            .alpha(alpha)
            .setDuration(duration)
            .withEndAction {
                ViewCompat.animate(views[1])
                    .scaleY(scaleY)
                    .scaleX(scaleX)
                    .alpha(alpha)
                    .setDuration(duration)
                    .withEndAction {
                        ViewCompat.animate(views[2])
                            .scaleY(scaleY)
                            .scaleX(scaleX)
                            .alpha(alpha)
                            .setDuration(duration)
                            .withEndAction {
                                ViewCompat.animate(views[3])
                                    .scaleY(scaleY)
                                    .scaleX(scaleX)
                                    .alpha(alpha)
                                    .setDuration(duration)
                                    .withEndAction {
                                        if (count % 2 == 0) {
                                            animateCorrectPasswordView(
                                                viewModel.pinClass.circles,
                                                1F, 1F, 1F, duration, deep, count + 1
                                            )
                                        } else {
                                            animateCorrectPasswordView(
                                                viewModel.pinClass.circles,
                                                0.7F, 0.7F, 0.4F, duration, deep, count + 1
                                            )
                                        }
                                    }
                            }
                    }
            }
    }

    private fun startAuthenticationMode() {
        _binding.text.visibility = View.INVISIBLE
        viewModel.funAfterPinWasEntered = {
            if (viewModel.userPreferences.userPinCode.toString() == it) {
                animateCorrectPasswordView(
                    viewModel.pinClass.circles,
                    0.7F, 0.7F, 0.4F, 70L, 4, 0
                )
            } else {
                for (circle in viewModel.pinClass.circles) {
                    animateIncorrectPasswordView(circle, 6, 140f, 20f)
                }
            }
        }
    }

    private fun initializeBiometricAuthentication() {
        executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult
                ) {
                    super.onAuthenticationSucceeded(result)
                    viewModel.pinClass.addAllPinCode()
                    animateCorrectPasswordView(
                        viewModel.pinClass.circles,
                        0.7F, 0.7F, 0.4F, 70L, 4, 0
                    )
                }
            })
        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG)
            .setTitle("Вход с отпечатком")
            .setSubtitle("Прикоснитесь к сенсору для входа по отпечатку")
            .setNegativeButtonText("Отмена")
            .build()
    }

    private fun initializeRecyclerViewAdapter() {
        val authenticationItemsForAdapter = AuthenticationItemsForAdapter()
        viewModel.loadItemsForAdapter(
            authenticationItemsForAdapter,
            biometricPrompt,
            promptInfo,
        )
        authenticationCardAdapter =
            AuthenticationCardAdapter(authenticationItemsForAdapter, authenticationMode)

    }


    private fun initializeRecyclerView() {
        initializeRecyclerViewAdapter()
        initializeButtonsRecyclerView()
    }

    fun changeAuthenticationDataAdapter() {
        val authenticationItemsForAdapter = AuthenticationItemsForAdapter()
        viewModel.loadItemsForAdapter(
            authenticationItemsForAdapter,
            biometricPrompt,
            promptInfo,

            )
        authenticationCardAdapter.authenticationItemsForAdapter = authenticationItemsForAdapter
        authenticationCardAdapter.notifyItemChanged(11)
    }

    private fun initializeButtonsRecyclerView() {
        val gridLayoutManager = GridLayoutManager(this, 3)
        with(_binding.buttonsRecyclerView) {
            layoutManager = gridLayoutManager
            setHasFixedSize(true)
            adapter = authenticationCardAdapter
        }
    }

    companion object{

        fun getFirstTimeAuthentication(context:Context):Intent{
            val intent = Intent(context, AuthenticationFragment::class.java)
            intent.putExtra("Mode", "INPUT_FIRST_TIME_PASSWORD_MODE")
            return intent
        }

        fun getSecondTimeAuthentication(context:Context,pin:String):Intent{
            val intent = Intent(context, AuthenticationFragment::class.java)
            intent.putExtra("Mode", "INPUT_SECOND_TIME_PASSWORD_MODE")
            intent.putExtra("Pin",pin)
            return intent
        }

        fun getAuthenticationIntent(context:Context):Intent{
            val intent = Intent(context, AuthenticationFragment::class.java)
            intent.putExtra("Mode", "AUTHENTICATION_MODE")
            return intent
        }
    }

}