package com.example.mvvmtodolist.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmtodolist.R
import com.example.mvvmtodolist.databinding.ActivityMainBinding
import com.example.mvvmtodolist.db.TaskDatabase
import com.example.mvvmtodolist.model.Task
import com.example.mvvmtodolist.ui.todo.TodoActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var taskRecyclerViewAdapter: MyRecyclerViewAdapter

    private lateinit var db: TaskDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        db = TaskDatabase.getInstance(this)!!

        taskRecyclerViewAdapter = MyRecyclerViewAdapter(this)
        binding.recyclerView.apply {
            adapter = taskRecyclerViewAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        binding.refreshLayout.setOnRefreshListener {
            getTaskFromDatabase()
            taskRecyclerViewAdapter.notifyDataSetChanged()
            binding.refreshLayout.isRefreshing = false
        }
    }

    override fun onResume() {
        super.onResume()
        getTaskFromDatabase()
        taskRecyclerViewAdapter.notifyDataSetChanged()
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

    private fun getTaskFromDatabase() {
        CoroutineScope(Dispatchers.IO).launch {
            taskRecyclerViewAdapter.tasks = db.taskDao().getAll() as MutableList<Task>
        }
    }
}