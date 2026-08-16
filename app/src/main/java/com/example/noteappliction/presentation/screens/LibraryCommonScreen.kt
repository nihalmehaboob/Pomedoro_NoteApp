package com.example.noteappliction.presentation.screens


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.noteappliction.presentation.viewModal.NoteViewModal


object LibraryRoutes {
    const val RESOURCE_SELECTION = "resource_selection"
    const val RECENT = "recent"
    const val NOTE_EDITOR = "note_editor"
    const val STICKY = "sticky"
}

@Composable
fun LibraryCommonScreen(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
    viewModel: NoteViewModal = hiltViewModel()
) {
    val notes by viewModel.notes.collectAsState()

    NavHost(
        navController = navController,
        startDestination = LibraryRoutes.RESOURCE_SELECTION
    ) {
        composable(LibraryRoutes.RESOURCE_SELECTION) {
            ResourceSelectionScreen(
                onRecentsClick = {
                    val recentNote = notes.lastOrNull()
                    if (recentNote != null) {
                        navController.navigate("${LibraryRoutes.NOTE_EDITOR}?noteId=${recentNote.id}")
                    }
                },
                onNewClick = { navController.navigate(LibraryRoutes.NOTE_EDITOR) },
                onStickyClick = { navController.navigate(LibraryRoutes.STICKY) }
            )
        }

        composable(LibraryRoutes.RECENT) {
            // This route might not be needed if we navigate directly, 
            // but we can keep it as a fallback or for a list view.
        }

        composable(
            route = "${LibraryRoutes.NOTE_EDITOR}?noteId={noteId}",
            arguments = listOf(
                navArgument("noteId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getInt("noteId") ?: -1
            NoteEditorScreen(
                noteId = if (noteId == -1) null else noteId,
                onNavigateBack = {
                    navController.navigate(LibraryRoutes.RESOURCE_SELECTION) {
                        popUpTo(LibraryRoutes.RESOURCE_SELECTION) { inclusive = true }
                    }
                },
                modifier = modifier
            )
        }

        composable(LibraryRoutes.STICKY) {
           // StickyScreen()
        }
    }
}