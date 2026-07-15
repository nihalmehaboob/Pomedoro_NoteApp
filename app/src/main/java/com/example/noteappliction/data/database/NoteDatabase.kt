package com.example.noteappliction.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.noteappliction.data.local.NoteDao
import com.example.noteappliction.data.local.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 1
)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

}