package com.nicolascristaldo.todolist.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.nicolascristaldo.todolist.R
import com.nicolascristaldo.todolist.data.database.TaskDatabase
import com.nicolascristaldo.todolist.data.repository.TaskRepository
import com.nicolascristaldo.todolist.ui.viewmodel.TaskViewModel
import com.nicolascristaldo.todolist.ui.viewmodel.TaskViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupViewModel()
    }

    private fun setupViewModel() {
        val taskRepository = TaskRepository(TaskDatabase.getDatabase(this))
        val viewModelProviderFactory = TaskViewModelFactory(taskRepository)

        taskViewModel = ViewModelProvider(this, viewModelProviderFactory)[TaskViewModel::class.java]
    }
}