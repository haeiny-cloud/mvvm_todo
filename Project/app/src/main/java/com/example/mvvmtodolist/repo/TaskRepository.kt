package com.example.mvvmtodolist.repo

import com.example.mvvmtodolist.dao.TaskDao
import com.example.mvvmtodolist.model.Task
import javax.inject.Inject

class TaskRepository @Inject constructor(private val dao: TaskDao) {

    fun getTasks(): List<Task> {
        return dao.getAll()
    }

    fun getTask(taskId: Int): Task {
        return dao.getTask(taskId)
    }

    fun saveTask(task: Task) {
        dao.saveTask(task)
    }

    fun deleteTask(task: Task) {
        dao.deleteTask(task)
    }
}