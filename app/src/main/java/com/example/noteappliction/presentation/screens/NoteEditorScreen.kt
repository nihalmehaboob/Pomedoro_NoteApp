package com.example.noteappliction.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.FormatListBulleted
import androidx.compose.material.icons.filled.FormatBold
import androidx.compose.material.icons.filled.FormatItalic
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.noteappliction.domain.entities.Note
import com.example.noteappliction.presentation.viewModal.NoteViewModal
import com.mohamedrejeb.richeditor.model.RichTextState
import com.mohamedrejeb.richeditor.model.rememberRichTextState
import com.mohamedrejeb.richeditor.ui.material3.RichTextEditor
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteEditorScreen(
    noteId: Int? = null,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: NoteViewModal = hiltViewModel()
) {
    val richTextState = rememberRichTextState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    
    val notes by viewModel.notes.collectAsState()
    val existingNote = remember(noteId, notes) { 
        noteId?.let { id -> notes.find { it.id == id } } 
    }

    LaunchedEffect(existingNote?.id) {
        existingNote?.let {
            richTextState.setHtml(it.content)
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        modifier = modifier
    ) { paddingValues ->
        NoteEditorContent(
            initialTitle = existingNote?.title ?: "",
            richTextState = richTextState,
            onNavigateBack = onNavigateBack,
            modifier = Modifier.padding(paddingValues),
        ) { title, content ->
            if (title.isNotEmpty()) {
                viewModel.addNote(
                    Note(
                        id = existingNote?.id ?: (0..Int.MAX_VALUE).random(),
                        title = title,
                        content = content,
                        author = "nihal",
                        topic = "topic"
                    )
                )
                onNavigateBack()
            } else {
                scope.launch {
                    snackbarHostState.showSnackbar("Please enter a note name")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NoteEditorContent(
    initialTitle: String = "",
    richTextState: RichTextState,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    onSaveClick: (String, String) -> Unit
) {
    var text by remember(initialTitle) { mutableStateOf(initialTitle) }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        FormattingToolbar(richTextState = richTextState)

        Row {
            TextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Enter Note name") },
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { onSaveClick(text, richTextState.toHtml()) }) {
                Text("save", fontSize= 10.sp)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        RichTextEditor(
            state = richTextState,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .padding(bottom = 8.dp),
            placeholder = { Text("Start writing...") }
        )

    }
}

@Composable
private fun FormattingToolbar(richTextState: RichTextState) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        IconToggleButton(
            checked = richTextState.currentSpanStyle.fontWeight == FontWeight.Bold,
            onCheckedChange = {
                richTextState.toggleSpanStyle(
                    SpanStyle(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        ) {
            Icon(Icons.Default.FormatBold, contentDescription = "Bold")
        }

        IconToggleButton(
            checked = richTextState.currentSpanStyle.fontStyle == FontStyle.Italic,
            onCheckedChange = {
                richTextState.toggleSpanStyle(
                    SpanStyle(
                        fontStyle = FontStyle.Italic
                    )
                )
            }
        ) {
            Icon(Icons.Default.FormatItalic, contentDescription = "Italic")
        }

        IconButton(
            onClick = { richTextState.toggleUnorderedList() }
        ) {
            Icon(Icons.AutoMirrored.Filled.FormatListBulleted, contentDescription = "Bulleted list")
        }
    }
}