package com.example.mvvmtodolist.ui.todo

import com.example.mvvmtodolist.data.entity.Task
import com.example.mvvmtodolist.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor() : BaseViewModel() {

    fun saveTask(task: Task) {
        CoroutineScope(Dispatchers.IO).launch {
            taskRepository.saveTask(task)
        }
    }
}