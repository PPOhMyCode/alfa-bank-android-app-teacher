package com.example.alfa_bank_android_app_teacher.ui.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.alfa_bank_android_app_teacher.R
import com.example.alfa_bank_android_app_teacher.domain.entities.SchoolClass
import com.example.alfa_bank_android_app_teacher.domain.entities.Student
import com.google.android.material.card.MaterialCardView

class StudentsListAdapter(var students: List<Student>) :
    RecyclerView.Adapter<StudentsListAdapter.ItemHolder>() {

    var onItemClick: ((Student) -> Unit)? = null
    var onCheckBoxClick: ((isEat:Boolean,Student) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = when (viewType) {
            IS_CHECKED -> LayoutInflater.from(parent.context)
                .inflate(R.layout.item_children, parent, false)
            IS_NOT_CHECKED -> LayoutInflater.from(parent.context)
                .inflate(R.layout.item_children, parent, false)
            else -> throw Exception()
        }

        return ItemHolder(view)
    }

    override fun getItemCount(): Int {
        return students.size
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val student = students[position]
        initializeCardView(holder, student)

        holder.checkBox.setOnCheckedChangeListener{buttonView, isChecked ->
            onCheckBoxClick?.invoke(isChecked,student)
        }

        holder.itemView.setOnClickListener {
            // Log.d(
            //     "Tag",students.toString()
            // )


            onItemClick?.invoke(student)

            //students[position].isChecked = !student.isChecked

            //holder.checkBox.isChecked=students[position].isChecked
            //notifyItemChanged(position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (students[position].isChecked) {
            true -> IS_CHECKED
            else -> IS_NOT_CHECKED
        }
    }

    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        var breakfastMaterialCardView: MaterialCardView =
            itemView.findViewById(R.id.breakfastMaterialCardView)
        var dinnerCardView: MaterialCardView = itemView.findViewById(R.id.dinnerCardView)
        var afternoonSnackMaterialCardView: MaterialCardView =
            itemView.findViewById(R.id.afternoonSnackMaterialCardView)
        var iLLMaterialCardView: MaterialCardView = itemView.findViewById(R.id.iLLMaterialCardView)
        val doesNotEatMaterialCardView: MaterialCardView = itemView.findViewById(R.id.doesnotEatMaterialCardView)
        var checkBox: CheckBox = itemView.findViewById(R.id.checkBox)
    }

    @SuppressLint("SetTextI18n")
    private fun initializeCardView(holder: ItemHolder, student: Student) {
        with(student) {
            holder.nameTextView.text = "$firstName $lastName"

            if (isEatBreakfast)
                holder.breakfastMaterialCardView.visibility = View.VISIBLE
            else
                holder.breakfastMaterialCardView.visibility = View.GONE

            if (isEatDinner)
                holder.dinnerCardView.visibility = View.VISIBLE
            else
                holder.dinnerCardView.visibility = View.GONE

            if (isEatAfternoonSnack)
                holder.afternoonSnackMaterialCardView.visibility = View.VISIBLE
            else
                holder.afternoonSnackMaterialCardView.visibility = View.GONE

            if (isIll)
                holder.iLLMaterialCardView.visibility = View.VISIBLE
            else
                holder.iLLMaterialCardView.visibility = View.GONE

            if(isNotEat)
                holder.doesNotEatMaterialCardView.visibility = View.VISIBLE
            else
                holder.doesNotEatMaterialCardView.visibility = View.GONE

            holder.checkBox.isChecked = isChecked
        }
    }

    companion object {
        private const val IS_CHECKED = 1
        private const val IS_NOT_CHECKED = 0
    }

}