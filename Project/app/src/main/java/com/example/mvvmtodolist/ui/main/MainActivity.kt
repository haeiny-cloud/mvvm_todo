package com.example.mvvmtodolist.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmtodolist.R
import com.example.mvvmtodolist.databinding.ActivityMainBinding
import com.example.mvvmtodolist.ui.detail.DetailActivity
import com.example.mvvmtodolist.ui.todo.TodoActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var taskRecyclerViewAdapter: MyRecyclerViewAdapter

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(binding.toolbar)

        binding.apply {
            lifecycleOwner = this@MainActivity
            vm = mainViewModel
        }

        taskRecyclerViewAdapter = MyRecyclerViewAdapter()

        taskRecyclerViewAdapter.setOnItemClickListener { view, task, position ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("taskId", task.taskId)
            startActivity(intent)
        }

        binding.recyclerView.apply {
            adapter = taskRecyclerViewAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        mainViewModel.tasks.observe(this) {
            mainViewModel.tasks.value?.let { taskRecyclerViewAdapter.submitList(it) }
        }

        binding.refreshLayout.setOnRefreshListener {
            mainViewModel.getTasks()
            binding.refreshLayout.isRefreshing = false
        }
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.getTasks()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_add) {
            val intent = Intent(this, TodoActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}