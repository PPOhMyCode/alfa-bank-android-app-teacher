package com.example.alfa_bank_android_app_teacher.ui.editdish

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.alfa_bank_android_app_teacher.databinding.ActivityChildBinding
import com.example.alfa_bank_android_app_teacher.databinding.ActivityEditOrderBinding
import com.example.alfa_bank_android_app_teacher.domain.entities.Dish
import com.example.alfa_bank_android_app_teacher.ui.adapters.DishListAdapter

class EditDishActivity: AppCompatActivity() {

    private lateinit var _binding: ActivityEditOrderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityEditOrderBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        val gridLayoutManager = GridLayoutManager(this, 2)

        val dishListAdapter = DishListAdapter("mode2")
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
            )
        )

        dishListAdapter.dishes = list2

        with(_binding.recyclerView2) {
            adapter = dishListAdapter
            layoutManager = gridLayoutManager
            false
        }

        val gridLayoutManager2 = GridLayoutManager(this, 2)

        val dishListAdapter2 = DishListAdapter("mode3")
        val list3  = listOf(
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
            )
        )

        dishListAdapter2.dishes = list3

        with(_binding.recyclerView3) {
            adapter = dishListAdapter2
            layoutManager = gridLayoutManager2
            false
        }
    }
}