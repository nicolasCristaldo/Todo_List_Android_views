package com.nicolascristaldo.todolist.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nicolascristaldo.todolist.model.Task

@Database(
    entities = [Task::class],
    version = 1,
    exportSchema = false
)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile
        private var Instance: TaskDatabase? = null

        fun getDatabase(ctx: Context): TaskDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(ctx, TaskDatabase::class.java, "products_db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}