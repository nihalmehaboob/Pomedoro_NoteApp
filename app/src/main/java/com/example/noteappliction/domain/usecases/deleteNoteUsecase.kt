package com.example.noteappliction.domain.usecases

import com.example.noteappliction.domain.entities.Note
import com.example.noteappliction.domain.repository.NoteRepository
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}
