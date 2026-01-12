package com.example.todolist.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.common.TODOList
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainFragment : Fragment() {
    private lateinit var rvTODOList: RecyclerView
    private lateinit var fabAddData: FloatingActionButton
    private lateinit var adapter: ToDoListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initTODOList()
        getDataFromInputFragment()
        fabAddData?.setOnClickListener {
            val bundle = bundleOf("toDoListData" to adapter.currentList.toTypedArray())
            findNavController().navigate(R.id.action_mainFragment_to_inputFragment, bundle)
        }
    }

    private fun initView() {
        view?.let {
            rvTODOList = it.findViewById(R.id.rv_todo_list)
            fabAddData = it.findViewById(R.id.fab_add_list)
            adapter = ToDoListAdapter()
        }
    }

    private fun initTODOList() {
        rvTODOList?.layoutManager = LinearLayoutManager(context)
        rvTODOList?.adapter = adapter
        adapter.setData(getDummyData())
    }

    private fun getDataFromInputFragment() {
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<List<TODOList>>("resultKey")
            ?.observe(viewLifecycleOwner) { result -> adapter.setData(result) }
    }

    private fun getDummyData(): List<TODOList> {
        return listOf(
            TODOList(),
            TODOList(
                name = "Aktivitas 1",
                dateline = "hari ini",
                description = "Ini deskripsi"
            )
        )
    }
}