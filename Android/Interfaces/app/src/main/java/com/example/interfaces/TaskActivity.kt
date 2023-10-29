package com.example.interfaces

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.interfaces.adapter.TaskAdapter
import com.example.interfaces.databinding.ActivityTaskBinding

class TaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
    }

    private fun initRecyclerView(){
        val manager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(this, manager.orientation)
        binding.recyclerTasks.layoutManager = manager
        binding.recyclerTasks.adapter = TaskAdapter(TaskProvider.taskList) { task ->
            onItemSelected(
                task
            )
        }
        binding.recyclerTasks.addItemDecoration(decoration)
    }

    fun onItemSelected(task : Task){
        Toast.makeText(this, task.nomTask, Toast.LENGTH_SHORT).show()
    }
}