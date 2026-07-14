package com.example.noteappliction.data.mapper

import com.example.noteappliction.data.local.NoteEntity
import com.example.noteappliction.data.remote.NoteDto
import com.example.noteappliction.domain.entities.Note

object NoteMapper {

    fun dtoToEntity(
        dto: NoteDto
    ): NoteEntity {

        return NoteEntity(
            dto.id,
            dto.title,
            dto.content,
            dto.author,
            dto.topic
        )

    }

    fun entityToDomain(
        entity: NoteEntity
    ): Note {

        return Note(
            entity.id,
            entity.title,
            entity.content,
            entity.author,
            entity.topic
        )

    }

    fun domainToEntity(
        note: Note
    ): NoteEntity {
        return NoteEntity(
            note.id,
            note.title,
            note.content,
            note.author,
            note.topic
        )
    }
    }
