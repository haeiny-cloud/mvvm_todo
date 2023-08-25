package com.example.mvvmtodolist.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmtodolist.data.entity.Task
import com.example.mvvmtodolist.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel() {

    private var _tasks: MutableLiveData<List<Task>> = MutableLiveData()
    val tasks: LiveData<List<Task>> get() = _tasks

    private var page = 1
    private var _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun getTask() {
        CoroutineScope(Dispatchers.IO).launch {
            _isLoading.postValue(true)
            delay(1000L)
            page = 1
            _tasks.postValue(taskRepository.getTasks(page))
            _isLoading.postValue(false)
        }
    }

    fun getMoreTask() {
        CoroutineScope(Dispatchers.IO).launch {
            _isLoading.postValue(true)
            delay(1000L)
            _tasks.postValue(_tasks.value?.plus(taskRepository.getTasks(++page)))
            _isLoading.postValue(false)
        }
    }
}