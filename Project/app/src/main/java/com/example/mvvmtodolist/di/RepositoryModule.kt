package com.example.mvvmtodolist.di

import com.example.mvvmtodolist.data.dao.TaskDao
import com.example.mvvmtodolist.repo.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    fun providesTaskRepository(dao: TaskDao): TaskRepository {
        return TaskRepository(dao)
    }
}