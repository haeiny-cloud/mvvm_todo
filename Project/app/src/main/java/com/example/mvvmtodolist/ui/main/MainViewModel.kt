package com.example.mvvmtodolist.ui.main

import com.example.mvvmtodolist.data.entity.Task
import com.example.mvvmtodolist.repo.TaskRepository
import com.example.mvvmtodolist.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) : BaseViewModel() {

    private var page = 1

    private var _tasks: MutableSharedFlow<List<Task>> = MutableSharedFlow()
    val tasks: SharedFlow<List<Task>> = _tasks.asSharedFlow()

    private var _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    fun getTask() {
        CoroutineScope(Dispatchers.IO).launch {
            _isLoading.emit(true)

            delay(1000L)
            page = 1
            _tasks.emit(taskRepository.getTasks(page))

            _isLoading.emit(false)
        }
    }

    fun getMoreTask() {
        CoroutineScope(Dispatchers.IO).launch {
            _isLoading.emit(true)

            delay(1000L)
            _tasks.emit(taskRepository.getTasks(++page))

            _isLoading.emit(false)
        }
    }

    init {
        getTask()
    }
}