package com.example.mvvmtodolist.ui.todo

import androidx.lifecycle.viewModelScope
import com.example.mvvmtodolist.data.entity.Task
import com.example.mvvmtodolist.repo.TaskRepository
import com.example.mvvmtodolist.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) : BaseViewModel() {

    fun saveTask(task: Task) {
        viewModelScope.launch {
            taskRepository.saveTask(task)
        }
    }
}