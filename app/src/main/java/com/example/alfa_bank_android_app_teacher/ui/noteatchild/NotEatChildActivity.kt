package com.example.alfa_bank_android_app_teacher.ui.noteatchild

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.alfa_bank_android_app_teacher.R
import com.example.alfa_bank_android_app_teacher.databinding.ActivityNotEatChildBinding
import com.example.alfa_bank_android_app_teacher.domain.entities.Child
import com.example.alfa_bank_android_app_teacher.domain.entities.SchoolClass
import com.example.alfa_bank_android_app_teacher.domain.entities.Student
import com.example.alfa_bank_android_app_teacher.ui.schoolclass.SchoolClassActivity
import com.example.alfa_bank_android_app_teacher.ui.schoolclass.SchoolClassViewModel

class NotEatChildActivity:AppCompatActivity() {

    private lateinit var binding: ActivityNotEatChildBinding
    private lateinit var viewModel: NotEatChildViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotEatChildBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeBackButton()
        viewModel = ViewModelProvider(this)[NotEatChildViewModel::class.java]
        intent.getParcelableExtra<Student>(Student)?.let {
            binding.toolbarTitle.text = "${it.firstName} ${it.lastName}"
        }
        initialize()
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

                viewModel.loadReason("3",binding.editText.text.toString()) // TODO:изменить id

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

    private fun initialize(){
        viewModel.text.observe(this) {
            Log.d("dafaf",it)
            binding.editText.text = it.toEditable()
        }

        viewModel.getReason("3") //TODO:изменить id
    }

    private fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)

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