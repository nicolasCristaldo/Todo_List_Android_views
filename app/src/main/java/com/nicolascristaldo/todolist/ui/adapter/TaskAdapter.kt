package com.nicolascristaldo.todolist.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nicolascristaldo.todolist.databinding.ItemTaskBinding
import com.nicolascristaldo.todolist.model.Task
import com.nicolascristaldo.todolist.ui.fragments.TaskListFragmentDirections

class TaskAdapter: RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    class TaskViewHolder(val taskBinding: ItemTaskBinding): RecyclerView.ViewHolder(taskBinding.root)

    private val differCallBack = object: DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.title == newItem.title &&
                    oldItem.description == newItem.description &&
                    oldItem.type == newItem.type
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentTask = differ.currentList[position]
        holder.taskBinding.tvTitle.text = currentTask.title
        holder.taskBinding.tvType.text = currentTask.type
        holder.taskBinding.tvDescription.text = currentTask.description

        holder.itemView.setOnClickListener {
            val direction = TaskListFragmentDirections.actionTaskListFragmentToEditTaskFragment(currentTask)
            it.findNavController().navigate(direction)
        }
    }
}