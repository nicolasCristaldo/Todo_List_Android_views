package com.nicolascristaldo.todolist.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolascristaldo.todolist.data.repository.TaskRepository
import com.nicolascristaldo.todolist.model.Task
import kotlinx.coroutines.launch

class TaskViewModel(
    private val taskRepository: TaskRepository
): ViewModel() {
    fun getAllTasks() = taskRepository.getAllTasks()

    fun insertTask(task: Task) = viewModelScope.launch {
        taskRepository.insertTask(task = task)
    }

    fun updateTask(task: Task) = viewModelScope.launch {
        taskRepository.updateTask(task = task)
    }

    fun deleteTask(task: Task) = viewModelScope.launch {
        taskRepository.deleteTask(task = task)
    }
}