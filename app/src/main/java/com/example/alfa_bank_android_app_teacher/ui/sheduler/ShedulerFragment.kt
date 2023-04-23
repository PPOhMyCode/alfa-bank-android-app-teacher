package com.example.alfa_bank_android_app_teacher.ui.sheduler

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alfa_bank_android_app_teacher.R

class ShedulerFragment : Fragment() {

    companion object {
        fun newInstance() = ShedulerFragment()
    }

    private lateinit var viewModel: ShedulerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sheduler, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ShedulerViewModel::class.java)
        // TODO: Use the ViewModel
    }

}