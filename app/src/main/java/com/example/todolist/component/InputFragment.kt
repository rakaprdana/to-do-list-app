package com.example.todolist.component

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.example.todolist.R
import com.example.todolist.common.TODOList
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import java.lang.Exception


class InputFragment : Fragment() {
    private val btnBack by lazy { view?.findViewById<ImageView>(R.id.iv_back_arrow) }
    private val inputNameList by lazy { view?.findViewById<TextInputEditText>(R.id.input_name_list) }
    private val inputDate by lazy { view?.findViewById<TextInputEditText>(R.id.input_dateline) }
    private val inputDescription by lazy { view?.findViewById<TextInputEditText>(R.id.input_description) }
    private val btnDiscard by lazy { view?.findViewById<MaterialButton>(R.id.btn_discard) }
    private val btnSave by lazy { view?.findViewById<MaterialButton>(R.id.btn_save) }
    private val toDoListData = mutableListOf<TODOList>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toDoListData.addAll(
            arguments?.getParcelableArray("toDoListData")
                ?.toMutableList() as MutableList<TODOList>
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_input, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnDiscard?.setOnClickListener {
            discardData()
        }
        btnSave?.setOnClickListener {
            if (isEntryValid()) {
                saveData()
            } else {
                showToast("Mohon lengkapi formulirnya")
            }
        }
        handleOnBackPressed()
        btnBack?.setOnClickListener { backToMainFragment() }
    }

    private fun showToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    private fun isEntryValid(): Boolean {
        return !(inputNameList?.text.toString().isBlank() || inputDate?.text.toString()
            .isBlank() || inputDescription?.text.toString().isBlank())
    }

    private fun discardData() {
        inputNameList?.setText("")
        inputDate?.setText("")
        inputDescription?.setText("")
    }

    private fun saveData() {
        val newDataEntry = TODOList(
            name = inputNameList?.text.toString(),
            dateline = inputDate?.text.toString(),
            description = inputDescription?.text.toString()
        )
        try {
            toDoListData.add(newDataEntry)
            discardData()
            showToast("Jadwal tersimpan")
        } catch (e: Exception) {
            showToast("Gagal menyimpan jadwal")
        }
    }

    private fun backToMainFragment() {
        val resultData = toDoListData
        findNavController().previousBackStackEntry?.savedStateHandle?.set("resultKey", resultData)
        findNavController().navigateUp()
    }

    private fun handleOnBackPressed() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                backToMainFragment()
            }
        }
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, callback)
    }
}