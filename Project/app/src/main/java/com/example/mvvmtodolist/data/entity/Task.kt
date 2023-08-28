package com.example.mvvmtodolist.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_task")
data class Task(
    @PrimaryKey(autoGenerate = true) var taskId: Int = 0,
    var title: String,
    var detail: String,
)