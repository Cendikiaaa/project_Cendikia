package com.example.coba.model


import android.content.Context
import com.example.coba.data.OfflineItemsRepository
import com.example.coba.data.TaskDatabase
import com.example.coba.data.TaskRepository

object TaskSingleton {
    @Volatile
    private var instance: TaskRepository? = null

    fun getTaskRepository(context: Context): TaskRepository {

        return instance ?: synchronized(this) {
            val database = TaskDatabase.getDatabase(context.applicationContext)
            OfflineItemsRepository(database.taskDao()).also { instance = it }
        }
    }
}