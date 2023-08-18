package com.example.mvvmtodolist

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvmtodolist.databinding.ActivityTodoBinding
import com.example.mvvmtodolist.model.Task

class TodoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTodoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener {
            Database.task.add(
                Task(binding.editTitle.text.toString(), binding.editDetail.text.toString())
            )

            Toast.makeText(this, "작성이 완료되었습니다.", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}