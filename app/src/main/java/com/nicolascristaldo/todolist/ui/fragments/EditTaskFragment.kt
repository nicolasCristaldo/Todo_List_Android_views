package com.nicolascristaldo.todolist.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.nicolascristaldo.todolist.R
import com.nicolascristaldo.todolist.databinding.FragmentEditTaskBinding
import com.nicolascristaldo.todolist.model.Task
import com.nicolascristaldo.todolist.ui.MainActivity
import com.nicolascristaldo.todolist.ui.viewmodel.TaskViewModel

class EditTaskFragment : Fragment() {
    private var _binding: FragmentEditTaskBinding? = null
    private val binding get() = _binding!!

    private lateinit var taskViewModel: TaskViewModel

    private lateinit var currentTask: Task

    private val args: EditTaskFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditTaskBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        taskViewModel = (activity as MainActivity).taskViewModel
        currentTask = args.task

        initUI()
        initListeners()
    }

    private fun initListeners() {
        binding.fabConfirm.setOnClickListener {
            if(editTask()) this.findNavController().popBackStack(R.id.taskListFragment, false)
        }

        binding.btnDelete.setOnClickListener {
            deleteTask()
        }
    }

    private fun initUI() {
        binding.etTitle.setText(currentTask.title)
        binding.etDescription.setText(currentTask.description)
    }

    private fun editTask(): Boolean {
        var isTaskEdited = false
        val taskTitle = binding.etTitle.text.trim()
        val taskDescription = binding.etDescription.text.trim()

        if(taskTitle.isNotEmpty()) {
            val task = Task(
                id = currentTask.id,
                title = taskTitle.toString(),
                description = taskDescription.toString(),
                type = ""
            )
            taskViewModel.updateTask(task = task)
            isTaskEdited = true
            Toast.makeText(this.context, "Task edited", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(this.context, "Please enter note title", Toast.LENGTH_SHORT).show()
        }

        return isTaskEdited
    }

    private fun deleteTask() {
        activity?.let {
            AlertDialog.Builder(it).apply {
                setTitle("Delete task")
                setMessage("Do you want to delete this task?")
                setPositiveButton("Delete") { _,_ ->
                    taskViewModel.deleteTask(currentTask)
                    view?.findNavController()?.popBackStack(R.id.taskListFragment, false)
                    Toast.makeText(context, "The task has been deleted", Toast.LENGTH_SHORT).show()
                }
                setNegativeButton("Cancel", null)
            }.create().show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}