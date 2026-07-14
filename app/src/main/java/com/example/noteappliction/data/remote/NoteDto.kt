package com.example.noteappliction.data.remote

import com.google.gson.annotations.SerializedName

//{
//    "note_id":1,
//    "note_title":"Compose",
//    "note_content":"LazyColumn",
//    "author_name":"Nihal",
//    "topic_name":"Android"
//}


data class NoteDto(

    @SerializedName("note_id")
    val id:Int,

    @SerializedName("note_title")
    val title:String,

    @SerializedName("note_content")
    val content:String,

    @SerializedName("author_name")
    val author:String,

    @SerializedName("topic_name")
    val topic:String

)