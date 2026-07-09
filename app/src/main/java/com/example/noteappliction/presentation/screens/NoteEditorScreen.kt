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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mohamedrejeb.richeditor.model.RichTextState
import com.mohamedrejeb.richeditor.model.rememberRichTextState
import com.mohamedrejeb.richeditor.ui.material3.RichTextEditor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteEditorScreen(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    // viewModel: NoteEditorViewModel = hiltViewModel()  // add when wiring VM
) {
    // For now, state lives here. Later this moves into the ViewModel
    // and gets exposed as a StateFlow<NoteEditorUiState>.
    val richTextState = rememberRichTextState()

    NoteEditorContent(
        richTextState = richTextState,
        onNavigateBack = onNavigateBack,
        modifier = modifier,
    ) {
        // Later: viewModel.onSaveClick(richTextState.toHtml())
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NoteEditorContent(
    richTextState: RichTextState,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    onSaveClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        FormattingToolbar(richTextState = richTextState)

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