package com.example.mvvmtodolist.repo

import com.example.mvvmtodolist.data.dao.TaskDao
import com.example.mvvmtodolist.data.entity.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TaskRepository @Inject constructor(private val dao: TaskDao) {

    suspend fun getTasks(page: Int): List<Task> {
        return withContext(Dispatchers.IO) {
            dao.getAll(page)
        }
    }

    suspend fun getTask(taskId: Int): Task {
        return withContext(Dispatchers.IO) {
            dao.getTask(taskId)
        }
    }

    suspend fun saveTask(task: Task) {
        withContext(Dispatchers.IO) {
            dao.saveTask(task)
        }
    }

    suspend fun deleteTask(task: Task) {
        withContext(Dispatchers.IO) {
            dao.deleteTask(task)
        }
    }
}