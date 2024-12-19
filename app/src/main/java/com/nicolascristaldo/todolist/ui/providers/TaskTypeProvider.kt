package com.nicolascristaldo.todolist.ui.providers

import com.nicolascristaldo.todolist.R

class TaskTypeProvider {
    fun getTypeColor(name: String): Int {
        return when(name) {
            "Personal" -> R.color.personal_color
            "Business" -> R.color.business_color
            "Job" -> R.color.job_color
            "Home" -> R.color.home_color
            "Social" -> R.color.social_color
            else -> R.color.other_color
        }
    }
}