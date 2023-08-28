package com.example.mvvmtodolist.ui.todo

import android.widget.Toast
import androidx.activity.viewModels
import com.example.mvvmtodolist.R
import com.example.mvvmtodolist.data.entity.Task
import com.example.mvvmtodolist.databinding.ActivityTodoBinding
import com.example.mvvmtodolist.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodoActivity : BaseActivity<ActivityTodoBinding, TodoViewModel>() {
    override val layoutId: Int = R.layout.activity_todo
    override val viewModel: TodoViewModel by viewModels()
    override fun setUp() {
        mViewDataBinding.btnAdd.setOnClickListener {
            val title = mViewDataBinding.editTitle.text.toString()
            val detail = mViewDataBinding.editDetail.text.toString()

            saveTask(title, detail)
        }
    }

    private fun saveTask(title: String, detail: String) {
        viewModel.saveTask(Task(0, title, detail))
        Toast.makeText(this, "작성이 완료되었습니다.", Toast.LENGTH_SHORT).show()
        finish()
    }
}