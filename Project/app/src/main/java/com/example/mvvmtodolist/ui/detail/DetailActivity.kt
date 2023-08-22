package com.example.mvvmtodolist.ui.detail

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvmtodolist.databinding.ActivityDetailBinding
import com.example.mvvmtodolist.data.entity.Task
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModels()

    private var task: Task? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)

        val taskId = intent.getIntExtra("taskId", -1)
        if (taskId == -1) errorCheck()

        detailViewModel.getTask(taskId)

        detailViewModel.task.observe(this) {
            task = it
            bindTask()
        }

        binding.btnDelete.setOnClickListener {
            deleteTask()
        }

        setContentView(binding.root)
    }

    private fun bindTask() {
        task = detailViewModel.task.value

        if (task == null) {
            errorCheck()
            return
        }

        binding.title.text = task!!.title
        binding.detail.text = task!!.detail
    }

    private fun deleteTask() {
        detailViewModel.deleteTask(task!!)
        Toast.makeText(this, "삭제가 완료되었습니다.", Toast.LENGTH_SHORT).show()
        finish()
    }
    private fun errorCheck() {
        Toast.makeText(this, "존재하지 않는 할일입니다.", Toast.LENGTH_SHORT).show()
        finish()
    }
}