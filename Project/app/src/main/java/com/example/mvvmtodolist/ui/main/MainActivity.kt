package com.example.mvvmtodolist.ui.main

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmtodolist.R
import com.example.mvvmtodolist.databinding.ActivityMainBinding
import com.example.mvvmtodolist.ui.base.BaseActivity
import com.example.mvvmtodolist.ui.detail.DetailActivity
import com.example.mvvmtodolist.ui.todo.TodoActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    if (!canScrollVertically(1)) {
                        viewModel.getMoreTask()
                    }
                }
            })
        }

        lifecycleScope.launch {
            viewModel.tasks.collect { tasks ->
                taskRecyclerViewAdapter.submitList(taskRecyclerViewAdapter.currentList + tasks)
            }
        }

        lifecycleScope.launch {
            viewModel.isLoading.collectLatest { isLoading ->
                if (isLoading)
                    mViewDataBinding.progressBar.visibility = View.VISIBLE
                else
                    mViewDataBinding.progressBar.visibility = View.GONE
            }
        }

        mViewDataBinding.refreshLayout.setOnRefreshListener {
            taskRecyclerViewAdapter.submitList(emptyList())
            viewModel.getTask()
            mViewDataBinding.refreshLayout.isRefreshing = false
        }
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