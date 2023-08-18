package com.example.mvvmtodolist

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvmtodolist.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val taskId = intent.getIntExtra("taskId", -1)

        if (taskId == -1) {
            Toast.makeText(this, "존재하지 않는 할일입니다.", Toast.LENGTH_SHORT).show()
            finish()
        }

        binding.title.text = Database.task[taskId].title
        binding.detail.text = Database.task[taskId].detail

        binding.btnDelete.setOnClickListener {
            removeTask(taskId)
        }
    }

    private fun removeTask(taskId: Int) {
        Database.task.removeAt(taskId)
        Toast.makeText(this, "삭제가 완료되었습니다.", Toast.LENGTH_SHORT).show()
        finish()
    }
}