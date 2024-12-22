package com.nicolascristaldo.todolist.ui.model

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

data class TaskTypeModel(
    @DrawableRes val icon: Int,
    @ColorRes val color: Int
)
