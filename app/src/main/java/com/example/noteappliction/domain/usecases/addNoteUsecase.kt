package com.example.noteappliction.domain.usecases

import com.example.noteappliction.domain.entities.Note
import com.example.noteappliction.domain.repository.NoteRepository
import javax.inject.Inject

class addNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(note: Note) {
        noteRepository.addNote(note)
    }
}
