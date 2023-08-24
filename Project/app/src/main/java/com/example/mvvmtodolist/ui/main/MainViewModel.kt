package com.example.mvvmtodolist.ui.main

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
class MainViewModel @Inject constructor() : BaseViewModel() {

    private var _tasks: MutableLiveData<MutableList<Task>> = MutableLiveData()
    val tasks: LiveData<MutableList<Task>> get() = _tasks

    fun getTasks() {
        CoroutineScope(Dispatchers.IO).launch {
            _tasks.postValue(taskRepository.getTasks() as MutableList<Task>)
        }
    }
}