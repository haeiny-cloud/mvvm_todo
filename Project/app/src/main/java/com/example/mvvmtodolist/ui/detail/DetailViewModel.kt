package com.example.mvvmtodolist.ui.detail

import androidx.lifecycle.viewModelScope
import com.example.mvvmtodolist.data.entity.Task
import com.example.mvvmtodolist.repo.TaskRepository
import com.example.mvvmtodolist.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) : BaseViewModel() {

    private var _task: MutableSharedFlow<Task> = MutableSharedFlow()
    val task: SharedFlow<Task> = _task.asSharedFlow()

    fun getTask(taskId: Int) {
        viewModelScope.launch {
            _task.emit(taskRepository.getTask(taskId))
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            taskRepository.deleteTask(task)
        }
    }
}