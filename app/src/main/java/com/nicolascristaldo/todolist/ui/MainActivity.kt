package com.nicolascristaldo.todolist.ui

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.nicolascristaldo.todolist.R
import com.nicolascristaldo.todolist.data.database.TaskDatabase
import com.nicolascristaldo.todolist.data.repository.TaskRepository
import com.nicolascristaldo.todolist.databinding.ActivityMainBinding
import com.nicolascristaldo.todolist.ui.viewmodel.TaskViewModel
import com.nicolascristaldo.todolist.ui.viewmodel.TaskViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var taskViewModel: TaskViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupViewModel()
        initListeners()
    }

    private fun initListeners() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.addTaskFragment -> {
                    binding.ivArrowBack.visibility = View.VISIBLE
                    binding.tvTitle.setText(R.string.add_task)
                }

                R.id.editTaskFragment -> {
                    binding.ivArrowBack.visibility = View.VISIBLE
                    binding.tvTitle.setText(R.string.edit_task)
                }

                else -> {
                    binding.ivArrowBack.visibility = View.GONE
                    binding.tvTitle.setText(R.string.app_name)
                }
            }
        }

        binding.ivArrowBack.setOnClickListener {
            navController.popBackStack(R.id.taskListFragment, false)
        }
    }

    private fun setupViewModel() {
        val taskRepository = TaskRepository(TaskDatabase.getDatabase(this))
        val viewModelProviderFactory = TaskViewModelFactory(taskRepository)

        taskViewModel = ViewModelProvider(this, viewModelProviderFactory)[TaskViewModel::class.java]
    }
}