package com.example.mvvmtodolist.ui.todo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvmtodolist.databinding.ActivityTodoBinding
import com.example.mvvmtodolist.model.Task
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTodoBinding
    private val todoViewModel: TodoViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener {
            val title = binding.editTitle.text.toString()
            val detail = binding.editDetail.text.toString()

            saveTask(title, detail)
            Toast.makeText(this, "작성이 완료되었습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveTask(title: String, detail: String) {
        val task = Task()
        task.title = title
        task.detail = detail

        todoViewModel.saveTask(task)
        finish()
    }
}