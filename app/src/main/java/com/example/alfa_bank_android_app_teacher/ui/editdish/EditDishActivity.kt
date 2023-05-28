package com.example.alfa_bank_android_app_teacher.ui.editdish

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.alfa_bank_android_app_teacher.databinding.ActivityChildBinding
import com.example.alfa_bank_android_app_teacher.databinding.ActivityEditOrderBinding
import com.example.alfa_bank_android_app_teacher.domain.entities.Dish
import com.example.alfa_bank_android_app_teacher.domain.entities.SchoolClass
import com.example.alfa_bank_android_app_teacher.domain.entities.Student
import com.example.alfa_bank_android_app_teacher.domain.entities.TypeMeal
import com.example.alfa_bank_android_app_teacher.ui.adapters.DishListAdapter
import com.example.alfa_bank_android_app_teacher.ui.child.ChildActivity
import com.example.alfa_bank_android_app_teacher.ui.classes.ClassesViewModel
import java.text.SimpleDateFormat
import java.util.*

class EditDishActivity: AppCompatActivity() {

    private lateinit var _binding: ActivityEditOrderBinding

    private var date =  SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(Date()).split(" ")[0].replace("/","-").replace("-5","-05").replace("-6","-06").split("-")

    private val viewModel by lazy {
        ViewModelProvider(this)[EditDishViewModel::class.java]
    }

    val dishListAdapter2 = DishListAdapter("mode3")
    val dishListAdapter = DishListAdapter("mode2")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityEditOrderBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        initialize()
        initializeCurrentDish()
        initializeMenuDish()
        (intent.getSerializableExtra(TypeMeal) as? TypeMeal)?.let {
            viewModel.loadMenu(it.key2)
        }
    }

    private fun initializeCurrentDish(){
        val gridLayoutManager = GridLayoutManager(this, 2)


        with(_binding.recyclerView2) {
            adapter = dishListAdapter
            layoutManager = gridLayoutManager
            false
        }

        dishListAdapter.onAddItemClick = {
                viewModel.addCurrentDish(dish = it,dishListAdapter2,dishListAdapter)
            }

        dishListAdapter.onDeleteItemClick = {
            viewModel.removeDish(it,dishListAdapter2,dishListAdapter)
        }

        viewModel.currentDishes.observe(this) {

            dishListAdapter.dishes = it
            dishListAdapter.notifyDataSetChanged()
        }

        _binding.recyclerView2.isNestedScrollingEnabled = false
    }

    private fun initializeMenuDish(){


        val gridLayoutManager2 = GridLayoutManager(this, 2)



        dishListAdapter2.onAddItemClick = {
            viewModel.addMenuDish(it,dishListAdapter2,dishListAdapter)
        }

        viewModel.menuDishes.observe(this) {

            dishListAdapter2.dishes = it
            dishListAdapter2.notifyDataSetChanged()
        }

        with(_binding.recyclerView3) {
            adapter = dishListAdapter2
            layoutManager = gridLayoutManager2
            false
        }

        _binding.recyclerView3.isNestedScrollingEnabled = false
    }

    private fun initialize(){
        (intent.getSerializableExtra(TypeMeal) as? TypeMeal)?.let {
           when(it){
               com.example.alfa_bank_android_app_teacher.domain.entities.TypeMeal.BREAKFAST ->{
                   _binding.breakfast.text = "Завтрак"
                   _binding.toolbarTitle.text="Изменение завтрака"
               }
               com.example.alfa_bank_android_app_teacher.domain.entities.TypeMeal.DINNER ->{
                   _binding.breakfast.text = "Обед"
                   _binding.toolbarTitle.text="Изменение обеда"
               }
               com.example.alfa_bank_android_app_teacher.domain.entities.TypeMeal.SNACK ->{
                   _binding.breakfast.text = "Полдник"
                   _binding.toolbarTitle.text="Изменение полдника"
               }
           }
        }
        _binding.buttonNav.setOnClickListener {
            val student = intent.getParcelableExtra(Student) as? Student
            val schoolClass = intent.getParcelableExtra(SchoolClass) as? SchoolClass
            startActivity(ChildActivity.newIntent(this,student!!,schoolClass!!))
            finish()
        }

        _binding.button3.setOnClickListener{
            (intent.getSerializableExtra(TypeMeal) as? TypeMeal)?.let {
                viewModel.saveButton(it.key2)
            }
        }

        _binding.textView4.text ="${date[0]}" +" "+ "${receipts[date[1].replace("0","").toInt()]}"

    }

    companion object{

        private const val Student = "Student"
        private const val TypeMeal = "TypeMeal"
        private const val SchoolClass = "SchoolClass"
        fun newIntent(packageContext: Context,student: Student,typeMeal: TypeMeal,schoolClass: SchoolClass): Intent {
            val intent = Intent(packageContext, EditDishActivity::class.java).apply {
                putExtra(Student,student)
                putExtra(TypeMeal,typeMeal)
                putExtra(SchoolClass,schoolClass)
            }
            return intent
        }

        private val receipts = mapOf(
            1 to "Января",
            2 to "Февраля",
            3 to "Марта",
            4 to "Апреля",
            5 to "Мая",
            6 to "Июня",
            7 to "Июля",
            8 to "Августа",
            9 to "Сентября",
            10 to "Октября",
            11 to "Ноября",
            12 to "Декабря",
        )
    }
}