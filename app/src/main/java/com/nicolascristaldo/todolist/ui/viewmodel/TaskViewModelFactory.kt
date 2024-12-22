package com.nicolascristaldo.todolist.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nicolascristaldo.todolist.data.repository.TaskRepository

class TaskViewModelFactory(
    private val taskRepository: TaskRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TaskViewModel(taskRepository) as T
    }
}