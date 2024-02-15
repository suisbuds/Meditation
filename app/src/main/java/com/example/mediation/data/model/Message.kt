package com.example.mediation.data.model

import androidx.room.PrimaryKey

data class Message(@PrimaryKey(autoGenerate = true) val id: Long, val content: String, val title: String)

