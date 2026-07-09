package com.example.noteappliction.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.noteappliction.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(){
    val drawblestate= rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope= rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawblestate,
        drawerContent = {
            SideBar(drawblestate,scope);
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