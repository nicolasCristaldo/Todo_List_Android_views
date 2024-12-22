package com.nicolascristaldo.todolist.ui.providers

import com.nicolascristaldo.todolist.R
import com.nicolascristaldo.todolist.ui.model.TaskTypeModel

class TaskTypeProvider {
    fun getTypeColor(name: String): TaskTypeModel {
        return when(name) {
            "Personal" -> TaskTypeModel(
                color = R.color.personal_color,
                icon = R.drawable.ic_personal
            )
            "Business" -> TaskTypeModel(
                color = R.color.business_color,
                icon = R.drawable.ic_business
            )
            "Job" -> TaskTypeModel(
                color = R.color.job_color,
                icon = R.drawable.ic_job
            )
            "Home" -> TaskTypeModel(
                color = R.color.home_color,
                icon = R.drawable.ic_home
            )
            "Social" -> TaskTypeModel(
                color = R.color.social_color,
                icon = R.drawable.ic_social
            )
            else -> TaskTypeModel(
                color = R.color.other_color,
                icon = R.drawable.ic_others
            )
        }
    }
}