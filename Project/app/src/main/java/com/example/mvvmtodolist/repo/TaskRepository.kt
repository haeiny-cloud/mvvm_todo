package com.example.mvvmtodolist.repo

import com.example.mvvmtodolist.data.dao.TaskDao
import com.example.mvvmtodolist.data.entity.Task
import javax.inject.Inject

class TaskRepository @Inject constructor(private val dao: TaskDao) {

    fun getTasks(page: Int): List<Task> {
        return dao.getAll(page)
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