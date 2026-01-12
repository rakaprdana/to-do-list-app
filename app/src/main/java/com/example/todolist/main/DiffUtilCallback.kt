package com.example.todolist.main

import androidx.recyclerview.widget.DiffUtil
import com.example.todolist.common.TODOList

class DiffUtilCallback : DiffUtil.ItemCallback<TODOList>() {
    override fun areItemsTheSame(oldItem: TODOList, newItem: TODOList): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TODOList, newItem: TODOList): Boolean {
        return oldItem == newItem
    }
}