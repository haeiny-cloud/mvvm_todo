package com.example.mvvmtodolist.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mvvmtodolist.dao.TaskDao
import com.example.mvvmtodolist.model.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}