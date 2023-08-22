package com.example.mvvmtodolist.ui.todo

import androidx.lifecycle.ViewModel
import com.example.mvvmtodolist.model.Task
import com.example.mvvmtodolist.repo.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) : ViewModel() {

    fun saveTask(task: Task) {
        CoroutineScope(Dispatchers.IO).launch {
            taskRepository.saveTask(task)
        }
    }
}