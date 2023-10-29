package com.example.interfaces.adapter

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.interfaces.Task
import com.example.interfaces.databinding.ItemTaskBinding

class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemTaskBinding.bind(view)

    fun render(task: Task, onClickListener: (Task) -> Unit) {
        binding.tvClientName.text = task.nomClient
        binding.tvTaskName.text = task.nomTask
        binding.tvDescTask.text = task.descTask
        binding.tvFechCreationTask.text = task.fechCreation
        binding.tvFechEndTask.text = task.fechFinalization
        itemView.setOnClickListener { onClickListener(task) }
    }
}