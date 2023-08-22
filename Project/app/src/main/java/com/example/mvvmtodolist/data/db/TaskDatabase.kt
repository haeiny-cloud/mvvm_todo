package com.example.mvvmtodolist.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mvvmtodolist.data.dao.TaskDao
import com.example.mvvmtodolist.data.entity.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}