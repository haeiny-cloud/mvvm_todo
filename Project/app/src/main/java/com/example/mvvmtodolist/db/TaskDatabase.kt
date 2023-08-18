package com.example.mvvmtodolist.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvmtodolist.dao.TaskDao
import com.example.mvvmtodolist.model.Task

@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    // 데이터 베이스 객체를 싱글톤으로 인스턴스.
    companion object {
        private var instance: TaskDatabase? = null

        @Synchronized
        fun getInstance(context: Context): TaskDatabase? {
            if (instance == null)
                synchronized(TaskDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TaskDatabase::class.java,
                        "task.db"
                    )
                        .build()
                }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }
    }
}