package com.example.todolist.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import com.example.todolist.R
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.common.TODOList

class ToDoListAdapter :
    ListAdapter<TODOList, ToDoListAdapter.TODOListViewHolder>(DiffUtilCallback()) {
    inner class TODOListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: TODOList) {
            val nameListView = itemView.findViewById<TextView>(R.id.tv_name_list)
            val timeView = itemView.findViewById<TextView>(R.id.tv_time)//perlu refact

            nameListView.text = item.name
            timeView.text = item.dateline
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TODOListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return TODOListViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: TODOListViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }

    fun setData(itemList: List<TODOList>) {
        submitList(itemList)
    }
}

