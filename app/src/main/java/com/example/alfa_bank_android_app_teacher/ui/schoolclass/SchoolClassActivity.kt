package com.example.alfa_bank_android_app_teacher.ui.schoolclass

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alfa_bank_android_app_teacher.MainActivity
import com.example.alfa_bank_android_app_teacher.R
import com.example.alfa_bank_android_app_teacher.databinding.ActivitySchoolClassBinding
import com.example.alfa_bank_android_app_teacher.domain.entities.SchoolClass
import com.example.alfa_bank_android_app_teacher.domain.entities.Student
import com.example.alfa_bank_android_app_teacher.ui.adapters.StudentsListAdapter
import com.example.alfa_bank_android_app_teacher.ui.child.ChildActivity
import com.example.alfa_bank_android_app_teacher.ui.noteatchild.NotEatChildActivity

class SchoolClassActivity : AppCompatActivity() {
    private lateinit var _binding: ActivitySchoolClassBinding
    private lateinit var viewModel: SchoolClassViewModel
    private lateinit var students: List<Student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySchoolClassBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        viewModel = ViewModelProvider(this)[SchoolClassViewModel::class.java]
        val grade = intent.getParcelableExtra<SchoolClass>("SCHOOL_CLASS")
        grade?.let {
            viewModel.loadStudents(it.gradeId)
        }

        findViewById<ImageView>(R.id.buttonNav).setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }

        viewModel.students.observe(this) {

            findViewById<ProgressBar>(R.id.progressBar2).visibility= View.GONE

            it?.let { it1 ->
                students = it1
                initializeRecyclerView(students)
            }
        }
        initializeButtonClick()

        _binding.checkBox.setOnCheckedChangeListener{ buttonView, isChecked ->
            if(isChecked){
                viewModel.addAllStudent()
            }else{
                viewModel.removeAllStudent()
            }
        }
    }

    private fun initializeButtonClick() {

        _binding.button.setOnClickListener {
            viewModel.makeOrder(students)
        }
        viewModel.status.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun initializeRecyclerView(students: List<Student>) {
        val studentsListAdapter = StudentsListAdapter(students)

        studentsListAdapter.onCheckBoxClick = {isEat, student ->
            if(isEat){
                viewModel.addStudent(student)
            }else{
                viewModel.removeStudent(student)
            }

        }

        studentsListAdapter.onItemClick ={
            if(it.isNotEat) {
                intent.getParcelableExtra<SchoolClass>(SCHOOL_CLASS)?.let { schoolClass->
                    startActivity(NotEatChildActivity.newIntent(this, it,schoolClass))
                    finish()
                }
            }
            else {
                intent.getParcelableExtra<SchoolClass>(SCHOOL_CLASS)?.let { schoolClass ->
                    startActivity(ChildActivity.newIntent(this,it,schoolClass))
                    finish()
                }
            }
        }

        _binding.studentsRecyclerView.adapter = studentsListAdapter
        _binding.studentsRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        _binding.studentsRecyclerView.addItemDecoration(
            DividerItemDecoration(
               this,
                DividerItemDecoration.VERTICAL,
            )
        )
    }


    companion object {
        private const val SCHOOL_CLASS = "SCHOOL_CLASS"

        fun newIntent(packageContext: Context, schoolClass: SchoolClass): Intent =
            Intent(packageContext, SchoolClassActivity::class.java).apply {
                putExtra(SCHOOL_CLASS, schoolClass)
            }
    }
}