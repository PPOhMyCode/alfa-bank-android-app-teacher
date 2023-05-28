package com.example.alfa_bank_android_app_teacher.ui.child

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.alfa_bank_android_app_teacher.databinding.ActivityChildBinding
import com.example.alfa_bank_android_app_teacher.databinding.ActivitySchoolClassBinding
import com.example.alfa_bank_android_app_teacher.domain.entities.Dish
import com.example.alfa_bank_android_app_teacher.domain.entities.SchoolClass
import com.example.alfa_bank_android_app_teacher.domain.entities.Student
import com.example.alfa_bank_android_app_teacher.domain.entities.TypeMeal
import com.example.alfa_bank_android_app_teacher.ui.adapters.DishListAdapter
import com.example.alfa_bank_android_app_teacher.ui.editdish.EditDishActivity
import com.example.alfa_bank_android_app_teacher.ui.noteatchild.NotEatChildActivity
import com.example.alfa_bank_android_app_teacher.ui.schoolclass.SchoolClassActivity
import com.example.alfa_bank_android_app_teacher.ui.schoolclass.SchoolClassViewModel

class ChildActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityChildBinding
    private lateinit var viewModel: ChildViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityChildBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        viewModel = ViewModelProvider(this)[ChildViewModel::class.java]
        viewModel.loadChildDish()
        initialize()
    }

    private fun initialize(){
        intent.getParcelableExtra<Student>(Student)?.let { student->
            _binding.toolbarTitle.text = "${student.firstName} ${student.lastName}"

            if(student.isEatBreakfast){
                initializeBreakfast()
            }
            if(student.isEatDinner){
                initializeDinner()
            }
            if(student.isEatAfternoonSnack){
                initializeSnack()
            }

        }
        _binding.buttonNav.setOnClickListener {
            intent.getParcelableExtra<SchoolClass>(SchoolClass)?.let { schoolClass ->
                startActivity(SchoolClassActivity.newIntent(this, schoolClass))
                finish()
            }
        }

    }

    private fun initializeBreakfast(){
        _binding.cardView2.visibility = View.VISIBLE
        _binding.imageViewBreakfast.visibility = View.VISIBLE
        _binding.recyclerView.visibility = View.VISIBLE

        _binding.cardView2.setOnClickListener{
            intent.getParcelableExtra<Student>(Student)?.let { student ->
                intent.getParcelableExtra<SchoolClass>(SchoolClass)?.let { schoolClass ->
                    val intent = EditDishActivity.newIntent(this, student, TypeMeal.BREAKFAST,schoolClass)
                    startActivity(intent)
                    finish()
                }
            }
        }

        _binding.imageViewBreakfast.setOnClickListener {
            intent.getParcelableExtra<Student>(Student)?.let { student ->
                intent.getParcelableExtra<SchoolClass>(SchoolClass)?.let { schoolClass ->
                    val intent = EditDishActivity.newIntent(this, student, TypeMeal.BREAKFAST,schoolClass)
                    startActivity(intent)
                    finish()
                }
            }
        }

        viewModel.breakfastDishes.observe(this){
            val gridLayoutManager = GridLayoutManager(this, 2)
            val adapter2 = DishListAdapter("mode1")

            adapter2.dishes = it

            with(_binding.recyclerView){
                adapter = adapter2
                layoutManager = gridLayoutManager
                isNestedScrollingEnabled = false
            }
        }

    }

    private fun initializeDinner(){
        _binding.cardView3.visibility = View.VISIBLE
        _binding.imageViewDinner.visibility = View.VISIBLE
        _binding.recyclerView2.visibility = View.VISIBLE


        _binding.cardView3.setOnClickListener{
            intent.getParcelableExtra<Student>(Student)?.let { student ->
                intent.getParcelableExtra<SchoolClass>(SchoolClass)?.let { schoolClass ->
                    val intent = EditDishActivity.newIntent(this, student, TypeMeal.DINNER,schoolClass)
                    startActivity(intent)
                    finish()
                }
            }
        }

        _binding.imageViewDinner.setOnClickListener {
            intent.getParcelableExtra<Student>(Student)?.let { student ->
                intent.getParcelableExtra<SchoolClass>(SchoolClass)?.let { schoolClass ->
                    val intent = EditDishActivity.newIntent(this, student, TypeMeal.DINNER,schoolClass)
                    startActivity(intent)
                    finish()
                }
            }
        }

        viewModel.breakfastDishes.observe(this){
            val gridLayoutManager = GridLayoutManager(this, 2)
            val adapter2 = DishListAdapter("mode1")

            adapter2.dishes = it

            with(_binding.recyclerView2){
                adapter = adapter2
                layoutManager = gridLayoutManager
                isNestedScrollingEnabled = false
            }
        }

    }

    private fun initializeSnack(){
        _binding.cardView4.visibility = View.VISIBLE
        _binding.imageViewSnack.visibility = View.VISIBLE
        _binding.recyclerView3.visibility = View.VISIBLE

        _binding.cardView4.setOnClickListener{
            intent.getParcelableExtra<Student>(Student)?.let { student ->
                intent.getParcelableExtra<SchoolClass>(SchoolClass)?.let { schoolClass ->
                    val intent = EditDishActivity.newIntent(this, student, TypeMeal.SNACK,schoolClass)
                    startActivity(intent)
                    finish()
                }
            }
        }

        _binding.imageViewSnack.setOnClickListener {
            intent.getParcelableExtra<Student>(Student)?.let { student ->
                intent.getParcelableExtra<SchoolClass>(SchoolClass)?.let { schoolClass ->
                    val intent = EditDishActivity.newIntent(this, student, TypeMeal.SNACK,schoolClass)
                    startActivity(intent)
                    finish()
                }
            }
        }




        viewModel.breakfastDishes.observe(this){
            val gridLayoutManager = GridLayoutManager(this, 2)
            val adapter2 = DishListAdapter("mode1")

            adapter2.dishes = it

            with(_binding.recyclerView3){
                adapter = adapter2
                layoutManager = gridLayoutManager
                isNestedScrollingEnabled = false
            }
        }
    }

    companion object {
        private const val SchoolClass = "SchoolClass"
        private const val Student = "Student"

        fun newIntent(packageContext: Context, student: Student, schoolClass: SchoolClass): Intent {
            val intent = Intent(packageContext, ChildActivity::class.java).apply {
                putExtra(SchoolClass,schoolClass)
                putExtra(Student,student)
            }
            return intent
        }
    }
}