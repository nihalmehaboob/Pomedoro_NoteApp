package com.example.noteappliction.data.local

import androidx.room3.Dao
import androidx.room3.Delete
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes")
    fun getAllNotes(): Flow<List<NoteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(
        note: NoteEntity
    )

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotes(
        notes: List<NoteEntity>
    )

    @Delete
    suspend fun deleteNote(
        note: NoteEntity
    )

}