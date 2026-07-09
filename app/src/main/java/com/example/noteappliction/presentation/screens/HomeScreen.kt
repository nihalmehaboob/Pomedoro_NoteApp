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
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.noteappliction.R
import com.example.noteappliction.domain.entities.Note
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(){
    val drawblestate= rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope= rememberCoroutineScope()

    val notes = remember {
        listOf(
            Note( title = "Note 1", description = "First note"),
            Note(
                title = "Note 2",
                description = "Second note",
            )
        )
    }

    ModalNavigationDrawer(
        drawerState = drawblestate,
        drawerContent = {
            SideBar(notes = notes,drawblestate, scope)
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
            Text(
                text = "Home Screen",
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}