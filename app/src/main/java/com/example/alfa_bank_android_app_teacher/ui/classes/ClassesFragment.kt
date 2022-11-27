package com.example.alfa_bank_android_app_teacher.ui.classes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alfa_bank_android_app_teacher.databinding.FragmentClassesBinding
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
        val homeViewModel =
            ViewModelProvider(this)[ClassesViewModel::class.java]

        _binding = FragmentClassesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //binding.childrenRecyclerView.adapter
        val childListAdapter = ClassesListAdapter(viewModel.loadSchoolClass())
        childListAdapter.onItemClick = {
            with(requireActivity()) {
                startActivity(SchoolClassActivity.newIntent(this,it))
                finish()
            }//viewModel.preferences.userChild = it
            //val intent = ChildActivity.newIntent(requireActivity(), it)
            //requireActivity().startActivity(intent)
            //requireActivity().finish()
        }
        val divider = MaterialDividerItemDecoration(
            requireActivity(),
            LinearLayoutManager.VERTICAL /*or LinearLayoutManager.HORIZONTAL*/
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