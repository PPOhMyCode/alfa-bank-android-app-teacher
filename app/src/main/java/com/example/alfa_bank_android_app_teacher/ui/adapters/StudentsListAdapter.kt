package com.example.alfa_bank_android_app_teacher.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.alfa_bank_android_app_teacher.R
import com.example.alfa_bank_android_app_teacher.domain.entities.SchoolClass
import com.example.alfa_bank_android_app_teacher.domain.entities.Student
import com.google.android.material.card.MaterialCardView

class StudentsListAdapter(var students: List<Student>) :
    RecyclerView.Adapter<StudentsListAdapter.ItemHolder>() {
    var onItemClick: ((Student) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_children, parent, false)
        return ItemHolder(view)
    }

    override fun getItemCount(): Int {
        return students.size
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val student = students[position]
        initializeCardView(holder, student)
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(student)
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
    }

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
        }
    }

}