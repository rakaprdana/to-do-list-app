package com.example.todolist.common


import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class TODOList(
    val id: String = UUID.randomUUID().toString(),
    val name: String = "Belajar Android",
    val dateline: String = "1 januari 2026 13:40",
    val description: String ="Membuat recycle view"
): Parcelable
