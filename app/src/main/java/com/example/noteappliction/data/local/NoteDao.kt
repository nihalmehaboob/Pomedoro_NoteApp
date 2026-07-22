package com.example.noteappliction.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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