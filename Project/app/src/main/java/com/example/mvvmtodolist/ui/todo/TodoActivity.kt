package com.example.mvvmtodolist.ui.todo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvmtodolist.databinding.ActivityTodoBinding
import com.example.mvvmtodolist.db.TaskDatabase
import com.example.mvvmtodolist.model.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTodoBinding

    private lateinit var db: TaskDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = TaskDatabase.getInstance(this)!!

        binding.btnAdd.setOnClickListener {
            saveTask(binding.editTitle.text.toString(), binding.editDetail.text.toString())
            Toast.makeText(this, "작성이 완료되었습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveTask(title: String, detail: String) {
        val task = Task()
        task.title = title
        task.detail = detail

        CoroutineScope(Dispatchers.IO).launch {
            db.taskDao().saveTask(task)
            finish()
        }
    }
}