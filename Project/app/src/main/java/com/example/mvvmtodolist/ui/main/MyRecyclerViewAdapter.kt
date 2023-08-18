package com.example.mvvmtodolist.ui.main

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmtodolist.databinding.RecyclerViewLayoutBinding
import com.example.mvvmtodolist.model.Task
import com.example.mvvmtodolist.ui.detail.DetailActivity

class MyRecyclerViewAdapter(
    private val context: Context
) : RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>() {

    var tasks = mutableListOf<Task>()

    inner class ViewHolder(
        private val binding: RecyclerViewLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Task) {
            binding.title.text = item.title
            binding.detail.text = item.detail

            binding.layout.setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("taskId", item.taskId)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RecyclerViewLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = tasks.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tasks[position])
    }
}