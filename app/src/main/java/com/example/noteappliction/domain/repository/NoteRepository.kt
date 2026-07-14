package com.example.noteappliction.domain.repository

import com.example.noteappliction.domain.entities.Note

interface NoteRepository{
    suspend fun getNotes(): List<Note>
    suspend fun addNote(note: Note)
    suspend fun deleteNote(note: Note)
}
