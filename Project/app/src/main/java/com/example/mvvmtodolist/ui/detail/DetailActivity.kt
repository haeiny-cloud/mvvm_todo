package com.example.mvvmtodolist.ui.detail

import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.mvvmtodolist.R
import com.example.mvvmtodolist.databinding.ActivityDetailBinding
import com.example.mvvmtodolist.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailActivity : BaseActivity<ActivityDetailBinding, DetailViewModel>() {
    override val layoutId: Int = R.layout.activity_detail
    override val viewModel: DetailViewModel by viewModels()
    override fun setUp() {
        val taskId = intent.getIntExtra("taskId", -1)
        if (taskId == -1) errorCheck()

        viewModel.getTask(taskId)

        lifecycleScope.launch {
            viewModel.task.collect {
                if (it.taskId == 0) errorCheck()
                mViewDataBinding.item = it
            }
        }

        mViewDataBinding.btnDelete.setOnClickListener {
            deleteTask()
        }
    }

    private fun deleteTask() {
        mViewDataBinding.item?.let { viewModel.deleteTask(it) }
        Toast.makeText(this, "삭제가 완료되었습니다.", Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun errorCheck() {
        Toast.makeText(this, "존재하지 않는 할일입니다.", Toast.LENGTH_SHORT).show()
        finish()
    }
}