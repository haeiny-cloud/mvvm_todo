package com.example.mvvmtodolist.ui.base

import androidx.lifecycle.ViewModel
import com.example.mvvmtodolist.repo.TaskRepository
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {
    @Inject
    protected lateinit var taskRepository: TaskRepository
}