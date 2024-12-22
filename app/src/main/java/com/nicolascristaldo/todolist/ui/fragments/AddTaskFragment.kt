package com.nicolascristaldo.todolist.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.nicolascristaldo.todolist.R
import com.nicolascristaldo.todolist.databinding.FragmentAddTaskBinding
import com.nicolascristaldo.todolist.model.Task
import com.nicolascristaldo.todolist.ui.MainActivity
import com.nicolascristaldo.todolist.ui.viewmodel.TaskViewModel

class AddTaskFragment : Fragment() {
    private var _binding: FragmentAddTaskBinding? = null
    private val binding get() = _binding!!

    private lateinit var taskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddTaskBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        taskViewModel = (activity as MainActivity).taskViewModel

        initListeners()
    }

    override fun onResume() {
        super.onResume()

        val types = resources.getStringArray(R.array.types)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_type_item, types)
        binding.tvType.setAdapter(arrayAdapter)
    }

    private fun initListeners() {
        binding.btnConfirm.setOnClickListener {
            if(saveTask()) it.findNavController().popBackStack(R.id.taskListFragment, false)
        }
    }

    private fun saveTask(): Boolean {
        var isTaskSaved = false
        val taskTitle = binding.etTitle.text.trim()
        val taskDescription = binding.etDescription.text.trim()
        val taskType = binding.tvType.text.trim()

        if(taskTitle.isNotEmpty()) {
            val task = Task(
                title = taskTitle.toString(),
                description = taskDescription.toString(),
                type = taskType.toString()
            )
            taskViewModel.insertTask(task)
            isTaskSaved = true
            Toast.makeText(this.context, "Task saved", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(this.context, "Please enter note title", Toast.LENGTH_SHORT).show()
        }
        return isTaskSaved
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}