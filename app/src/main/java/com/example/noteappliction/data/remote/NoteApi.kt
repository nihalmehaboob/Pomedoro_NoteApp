package com.example.noteappliction.data.remote

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface NoteApi {

    @GET("notes")
    suspend fun getNotes(): List<NoteDto>

    @POST("notes")
    suspend fun addNote(
        @Body note: NoteDto
    )

    @DELETE("notes/{id}")
    suspend fun deleteNote(
        @Path("id") id:Int
    )

}