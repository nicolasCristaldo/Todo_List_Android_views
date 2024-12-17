package com.nicolascristaldo.todolist.data.repository

import com.nicolascristaldo.todolist.data.database.TaskDatabase
import com.nicolascristaldo.todolist.model.Task

class TaskRepository(private val database: TaskDatabase) {
    fun getAllTasks() = database.taskDao().getAllTasks()

    suspend fun insertTask(task: Task) = database.taskDao().insertTask(task = task)

    suspend fun updateTask(task: Task) = database.taskDao().updateTask(task = task)

    suspend fun deleteTask(task: Task) = database.taskDao().deleteTask(task = task)
}