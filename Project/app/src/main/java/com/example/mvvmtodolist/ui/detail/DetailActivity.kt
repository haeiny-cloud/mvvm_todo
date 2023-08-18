package com.example.mvvmtodolist.ui.detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvmtodolist.databinding.ActivityDetailBinding
import com.example.mvvmtodolist.db.TaskDatabase
import com.example.mvvmtodolist.model.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    private lateinit var db: TaskDatabase
    private lateinit var task: Task

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = TaskDatabase.getInstance(this)!!

        val taskId = intent.getIntExtra("taskId", -1)

        if (taskId == -1) {
            Toast.makeText(this, "존재하지 않는 할일입니다.", Toast.LENGTH_SHORT).show()
            finish()
        }

        getTask(taskId)

        binding.btnDelete.setOnClickListener {
            removeTask()
        }
    }

    private fun getTask(taskId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            task = db.taskDao().getTask(taskId)

            binding.title.text = task.title
            binding.detail.text = task.detail
        }
    }

    private fun removeTask() {
        CoroutineScope(Dispatchers.IO).launch {
            db.taskDao().deleteTask(task)
            finish()
        }
    }
}