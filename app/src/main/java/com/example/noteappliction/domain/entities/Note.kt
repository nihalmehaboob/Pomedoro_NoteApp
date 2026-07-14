package com.example.noteappliction.domain.entities

data class Note(
    val id: Int,
    val title: String,
    val content: String,
    val author: String,
    val topic: String
)
