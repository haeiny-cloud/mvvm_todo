package com.example.mvvmtodolist.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmtodolist.data.entity.Task
import com.example.mvvmtodolist.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor() : BaseViewModel() {

    private var _task: MutableLiveData<Task> = MutableLiveData()
    val task: LiveData<Task> get() = _task

    fun getTask(taskId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            _task.postValue(taskRepository.getTask(taskId))
        }
    }

    fun deleteTask(task: Task) {
        CoroutineScope(Dispatchers.IO).launch {
            taskRepository.deleteTask(task)
        }
    }

}