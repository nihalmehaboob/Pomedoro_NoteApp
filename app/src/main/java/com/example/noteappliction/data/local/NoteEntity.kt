package com.example.noteappliction.data.local

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity(

    @PrimaryKey
    val id:Int,

    val title:String,

    val content:String,

    val author:String,

    val topic:String

)