package com.example.mvvmtodolist.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvmtodolist.data.entity.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM table_task ORDER BY taskId DESC")
    fun getAll(): List<Task>

    // taskId를 통해 task 가져오기
    @Query("SELECT * FROM table_task WHERE taskId = :taskId")
    fun getTask(taskId: Int): Task

    // 중복 값 충돌 발생 시 새로 들어온 데이터로 교체
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveTask(task: Task)

    // task 삭제
    @Delete
    fun deleteTask(task: Task)
}