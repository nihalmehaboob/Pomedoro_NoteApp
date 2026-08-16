package com.example.noteappliction.presentation.viewModal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteappliction.domain.entities.Note
import com.example.noteappliction.domain.usecases.DeleteNoteUseCase
import com.example.noteappliction.domain.usecases.GetNotesUseCase
import com.example.noteappliction.domain.usecases.addNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModal @Inject constructor(
    private val getNotesUseCase: GetNotesUseCase,
    private val addNoteUseCase: addNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : ViewModel() {

    val notes: StateFlow<List<Note>> = getNotesUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addNote(note: Note) {
        viewModelScope.launch {
            addNoteUseCase(note)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            deleteNoteUseCase(note)
        }
    }

    fun getNoteById(id: Int): Note? {
        return notes.value.find { it.id == id }
    }
}
