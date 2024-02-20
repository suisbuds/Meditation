package com.example.meditation.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Message(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val content: String,
    val title: String,
    val time: String
)

