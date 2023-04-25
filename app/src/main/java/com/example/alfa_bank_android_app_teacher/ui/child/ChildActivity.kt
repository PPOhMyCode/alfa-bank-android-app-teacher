package com.example.alfa_bank_android_app_teacher.ui.child

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.alfa_bank_android_app_teacher.databinding.ActivityChildBinding
import com.example.alfa_bank_android_app_teacher.databinding.ActivitySchoolClassBinding
import com.example.alfa_bank_android_app_teacher.domain.entities.Dish
import com.example.alfa_bank_android_app_teacher.domain.entities.SchoolClass
import com.example.alfa_bank_android_app_teacher.ui.adapters.DishListAdapter
import com.example.alfa_bank_android_app_teacher.ui.editdish.EditDishActivity
import com.example.alfa_bank_android_app_teacher.ui.schoolclass.SchoolClassActivity

class ChildActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityChildBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityChildBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        val gridLayoutManager = GridLayoutManager(this, 2)

        val dishListAdapter = DishListAdapter("mode1")
        val list2  = listOf(
            Dish(
                1,
                "Каша овсяная",
                "asd",
                1.0f,
                85f,
                1f,
                1f,
                1f,
                1f
            ),
            Dish(
                2,
                "Пюре",
                "asd",
                1.0f,
                7f,
                1f,
                1f,
                1f,
                1f
            ),
            Dish(
                3,
                "Пирожок",
                "asd",
                1.0f,
                20f,
                1f,
                1f,
                1f,
                1f
            )
        )

        dishListAdapter.dishes = list2

        with(_binding.recyclerView) {
            adapter = dishListAdapter
            layoutManager = gridLayoutManager
            false
        }

        val gridLayoutManager2 = GridLayoutManager(this, 2)

        val dishListAdapter2 = DishListAdapter("mode1")

        dishListAdapter2.dishes = list2

        with(_binding.recyclerView2) {
            adapter = dishListAdapter2
            layoutManager = gridLayoutManager2
            false
        }

        val gridLayoutManager3 = GridLayoutManager(this, 2)

        val dishListAdapter3 = DishListAdapter("mode1")

        dishListAdapter3.dishes = list2

        with(_binding.recyclerView3) {
            adapter = dishListAdapter3
            layoutManager = gridLayoutManager3
            false
        }

        _binding.recyclerView

        _binding.breakfast.setOnClickListener {
            startActivity(Intent(this,EditDishActivity::class.java))
            finish()
        }

    }

    companion object {
        fun newIntent(packageContext: Context): Intent {
            val intent = Intent(packageContext, ChildActivity::class.java)
            return intent
        }
    }
}