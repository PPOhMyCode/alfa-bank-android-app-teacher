package com.example.alfa_bank_android_app_teacher.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.alfa_bank_android_app_teacher.R
import com.example.alfa_bank_android_app_teacher.domain.entities.SchoolClass

class ClassesListAdapter(var clesses: List<SchoolClass>) :
    RecyclerView.Adapter<ClassesListAdapter.ItemHolder>() {
    var onItemClick: ((SchoolClass) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_class, parent, false)
        return ItemHolder(view)
    }

    override fun getItemCount(): Int {
        return clesses.size
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        with(clesses[position]) {
            holder.schoolClassTextView.text = schoolClass
            holder.school.text = school
        }
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(clesses[position])
        }
    }

    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var schoolClassTextView: TextView = itemView.findViewById(R.id.schoolClassTextView)
        var school: TextView = itemView.findViewById(R.id.schoolTextView)
    }

}