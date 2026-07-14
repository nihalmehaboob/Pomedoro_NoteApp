package com.example.noteappliction.data.repository

import com.example.noteappliction.data.local.NoteDao
import com.example.noteappliction.data.mapper.NoteMapper
import com.example.noteappliction.data.remote.NoteApi
import com.example.noteappliction.domain.entities.Note
import com.example.noteappliction.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NoteRepositoryImpl(
    private val noteApi: NoteApi,
    private val noteDao: NoteDao
) : NoteRepository{

    //reading from api
    override suspend fun getNotes(): Flow<List<Note>> {
        return noteDao.getAllNotes().map { entities ->
            entities.map { it->
                NoteMapper.entityToDomain(it)
            }
        }
    }
    //inserting
    override suspend fun addNote(note: Note) {
        noteDao.insertNote(NoteMapper.domainToEntity(note))
    }
    //deleting
    override suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(NoteMapper.domainToEntity(note))
    }
}