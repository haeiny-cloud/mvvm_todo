package com.example.mvvmtodolist.ui.main

import androidx.recyclerview.widget.DiffUtil
import com.example.mvvmtodolist.R
import com.example.mvvmtodolist.data.entity.Task
import com.example.mvvmtodolist.ui.base.BaseRecyclerViewAdapter

class MyRecyclerViewAdapter : BaseRecyclerViewAdapter<Task>(TaskDiffUtil()) {
    class TaskDiffUtil : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.taskId == newItem.taskId
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }
    }

    override fun getItemViewType(position: Int): Int = R.layout.recycler_view_layout
}