package com.example.mvvmtodolist.ui.main

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmtodolist.R
import com.example.mvvmtodolist.databinding.ActivityMainBinding
import com.example.mvvmtodolist.ui.base.BaseActivity
import com.example.mvvmtodolist.ui.detail.DetailActivity
import com.example.mvvmtodolist.ui.todo.TodoActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    private lateinit var taskRecyclerViewAdapter: MyRecyclerViewAdapter

    override val layoutId: Int = R.layout.activity_main
    override val viewModel: MainViewModel by viewModels()
    override fun setUp() {
        setSupportActionBar(mViewDataBinding.toolbar)

        taskRecyclerViewAdapter = MyRecyclerViewAdapter()

        taskRecyclerViewAdapter.setOnItemClickListener { view, task, position ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("taskId", task.taskId)
            startActivity(intent)
        }

        mViewDataBinding.recyclerView.apply {
            adapter = taskRecyclerViewAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        viewModel.tasks.observe(this) {
            viewModel.tasks.value?.let { taskRecyclerViewAdapter.submitList(it) }
        }

        mViewDataBinding.refreshLayout.setOnRefreshListener {
            viewModel.getTasks()
            mViewDataBinding.refreshLayout.isRefreshing = false
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getTasks()
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