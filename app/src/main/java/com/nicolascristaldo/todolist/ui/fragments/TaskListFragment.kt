package com.nicolascristaldo.todolist.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.nicolascristaldo.todolist.R
import com.nicolascristaldo.todolist.databinding.FragmentTaskListBinding
import com.nicolascristaldo.todolist.ui.MainActivity
import com.nicolascristaldo.todolist.ui.adapter.TaskAdapter
import com.nicolascristaldo.todolist.ui.viewmodel.TaskViewModel
import kotlinx.coroutines.launch


class TaskListFragment : Fragment() {

    private var _binding: FragmentTaskListBinding? = null
    private val binding get() = _binding!!

    private lateinit var taskViewModel: TaskViewModel
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        taskViewModel = (activity as MainActivity).taskViewModel
        setupHomeRecyclerView()

        initListeners()
    }

    private fun initListeners() {
        binding.btnAddTask.setOnClickListener {
            it.findNavController().navigate(R.id.action_taskListFragment_to_addTaskFragment)
        }
    }

    private fun setupHomeRecyclerView() {
        taskAdapter = TaskAdapter()
        binding.rvTasks.apply {
            layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            adapter = taskAdapter
        }

        lifecycleScope.launch {
            activity?.let {
                taskViewModel.getAllTasks().collect {
                    taskAdapter.differ.submitList(it)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}