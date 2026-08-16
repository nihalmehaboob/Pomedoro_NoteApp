package com.example.noteappliction.presentation.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.noteappliction.R
import com.example.noteappliction.presentation.viewModal.NoteViewModal
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: NoteViewModal = hiltViewModel()
){
    val drawblestate= rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope= rememberCoroutineScope()
    val notes by viewModel.notes.collectAsState()
    val navController = rememberNavController()

    ModalNavigationDrawer(
        drawerState = drawblestate,
        drawerContent = {
            SideBar(
                notes = notes,
                drawerState = drawblestate,
                scope = scope,
                onNoteClick = { note ->
                    navController.navigate("${LibraryRoutes.NOTE_EDITOR}?noteId=${note.id}")
                },
                onDeleteNoteClick = { note ->
                    viewModel.deleteNote(note)
                },
                onAddNoteClick = {
                    navController.navigate(LibraryRoutes.NOTE_EDITOR)
                }
            )
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Home") },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawblestate.open()
                            }
                        }) {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                painter = painterResource(id = R.drawable.menuburger),
                                contentDescription = "My Icon"
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->
            LibraryCommonScreen(navController, modifier = Modifier.padding(innerPadding))
        }
    }
}
