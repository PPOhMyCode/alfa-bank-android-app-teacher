package com.example.alfa_bank_android_app_teacher.ui.noteatchild

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.alfa_bank_android_app_teacher.R
import com.example.alfa_bank_android_app_teacher.databinding.ActivityNotEatChildBinding
import com.example.alfa_bank_android_app_teacher.domain.entities.Child
import com.example.alfa_bank_android_app_teacher.domain.entities.SchoolClass
import com.example.alfa_bank_android_app_teacher.domain.entities.Student
import com.example.alfa_bank_android_app_teacher.ui.schoolclass.SchoolClassActivity

class NotEatChildActivity:AppCompatActivity() {

    private lateinit var binding: ActivityNotEatChildBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotEatChildBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeBackButton()
        intent.getParcelableExtra<Student>(Student)?.let {
            binding.toolbarTitle.text = "${it.firstName} ${it.lastName}"
        }
    }

    private fun initializeBackButton(){

        findViewById<ImageView>(R.id.buttonNav).setOnClickListener {
            val builder = android.app.AlertDialog.Builder(this)
            builder.setTitle("School food")
            builder.setMessage("Сохранить изменения?")
            builder.setCancelable(true)
            builder.setPositiveButton(
                "Да"
            ) { _, _ ->
                intent.getParcelableExtra<SchoolClass>(SchoolClass)?.let {
                    startActivity(SchoolClassActivity.newIntent(this, it))
                    finish()
                }

                Toast.makeText(this,"Изменения успешно сохранены",Toast.LENGTH_SHORT).show()
            }
            builder.setNegativeButton("Нет") { _, _ ->
                intent.getParcelableExtra<SchoolClass>(SchoolClass)?.let {
                    startActivity(SchoolClassActivity.newIntent(this, it))
                    finish()
                }
            }
            builder.show()

        }
    }

    companion object{
        private const val Student = "Student"
        private const val SchoolClass = "SchoolClass"

        fun newIntent(packageContext: Context, student: Student,schoolClass: SchoolClass): Intent =
            Intent(packageContext, NotEatChildActivity::class.java).apply {
                putExtra(Student, student)
                putExtra(SchoolClass,schoolClass)
            }
    }
}