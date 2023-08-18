package com.example.mvvmtodolist

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmtodolist.databinding.ActivityMainBinding
import java.lang.Thread.sleep

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var taskRecyclerViewAdapter: MyRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        taskRecyclerViewAdapter = MyRecyclerViewAdapter(this)
        binding.recyclerView.apply {
            adapter = taskRecyclerViewAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        binding.refreshLayout.setOnRefreshListener {
            getTaskFromDatabase()
            binding.refreshLayout.isRefreshing = false
        }
    }

    override fun onResume() {
        super.onResume()
        getTaskFromDatabase()
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
        taskRecyclerViewAdapter.tasks = Database.task
        sleep(100L) // 자체 딜레이
        taskRecyclerViewAdapter.notifyDataSetChanged()
    }
}