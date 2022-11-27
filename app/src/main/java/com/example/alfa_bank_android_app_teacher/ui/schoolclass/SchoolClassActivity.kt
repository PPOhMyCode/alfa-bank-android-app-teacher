package com.example.alfa_bank_android_app_teacher.ui.schoolclass

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alfa_bank_android_app_teacher.R
import com.example.alfa_bank_android_app_teacher.databinding.ActivitySchoolClassBinding
import com.example.alfa_bank_android_app_teacher.domain.entities.SchoolClass
import com.example.alfa_bank_android_app_teacher.ui.adapters.StudentsListAdapter

class SchoolClassActivity : AppCompatActivity() {
    private lateinit var _binding: ActivitySchoolClassBinding
    private lateinit var viewModel: SchoolClassViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySchoolClassBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        viewModel = ViewModelProvider(this)[SchoolClassViewModel::class.java]
        initializeRecyclerView()
    }

    private fun initializeRecyclerView() {
        val studentsListAdapter = StudentsListAdapter(viewModel.loadStudents())
        _binding.studentsRecyclerView.adapter = studentsListAdapter
        _binding.studentsRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }


    companion object {
        private const val SCHOOL_CLASS = "SCHOOL_CLASS"

        fun newIntent(packageContext: Context, schoolClass: SchoolClass): Intent =
            Intent(packageContext, SchoolClassActivity::class.java).apply {
                putExtra(SCHOOL_CLASS, schoolClass)
            }
    }
}