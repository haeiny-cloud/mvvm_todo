package com.example.mvvmtodolist.di

import android.content.Context
import androidx.room.Room
import com.example.mvvmtodolist.data.dao.TaskDao
import com.example.mvvmtodolist.data.db.TaskDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesTaskDatabase(@ApplicationContext context: Context): TaskDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            TaskDatabase::class.java,
            "task.db"
        ).build()
    }

    @Provides
    @Singleton
    fun providesTaskDao(database: TaskDatabase): TaskDao {
        return database.taskDao()
    }
}