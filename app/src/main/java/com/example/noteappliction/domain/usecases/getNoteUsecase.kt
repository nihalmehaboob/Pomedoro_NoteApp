package com.example.noteappliction.domain.usecases

import com.example.noteappliction.domain.entities.Note
import com.example.noteappliction.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    operator fun invoke(): Flow<List<Note>> {
        return repository.getNotes()
    }
}
