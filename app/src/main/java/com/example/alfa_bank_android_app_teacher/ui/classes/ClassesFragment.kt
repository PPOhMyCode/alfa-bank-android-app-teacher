package com.example.alfa_bank_android_app_teacher.ui.classes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alfa_bank_android_app_teacher.R
import com.example.alfa_bank_android_app_teacher.databinding.FragmentClassesBinding
import com.example.alfa_bank_android_app_teacher.domain.entities.SchoolClass
import com.example.alfa_bank_android_app_teacher.ui.adapters.ClassesListAdapter
import com.example.alfa_bank_android_app_teacher.ui.schoolclass.SchoolClassActivity
import com.google.android.material.divider.MaterialDividerItemDecoration

class ClassesFragment : Fragment() {

    private var _binding: FragmentClassesBinding? = null

    private val viewModel by lazy {
        ViewModelProvider(this)[ClassesViewModel::class.java]
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClassesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadSchoolClass()
        viewModel.schoolClasses.observe(requireActivity()) {
            it?.let {
                initializeRecyclerView(it)
                view.findViewById<ProgressBar>(R.id.progressBar3)?.visibility=View.GONE
            }
        }
    }

    private fun initializeRecyclerView(classes: List<SchoolClass>) {
        val childListAdapter = ClassesListAdapter(classes)
        childListAdapter.onItemClick = {
            with(requireActivity()) {
                val intent =SchoolClassActivity.newIntent(this, it)
                intent.putExtra("Id",it.gradeId)
                startActivity(intent)
                finish()
            }
        }

        val divider = MaterialDividerItemDecoration(
            requireActivity(),
            LinearLayoutManager.VERTICAL
        )

        divider.isLastItemDecorated = false
        with(binding.childrenRecyclerView) {
            adapter = childListAdapter
            layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            false
            addItemDecoration(divider)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}