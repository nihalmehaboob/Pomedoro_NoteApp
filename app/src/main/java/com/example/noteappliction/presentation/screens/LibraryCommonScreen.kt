package com.example.noteappliction.presentation.screens


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


private object LibraryRoutes {
    const val RESOURCE_SELECTION = "resource_selection"
    const val RECENT = "recent"
    const val NEW = "new"
    const val STICKY = "sticky"
}

@Composable
fun LibraryCommonScreen(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = LibraryRoutes.RESOURCE_SELECTION
    ) {
        composable(LibraryRoutes.RESOURCE_SELECTION) {
            ResourceSelectionScreen(
                onRecentsClick = { navController.navigate(LibraryRoutes.RECENT) },
                onNewClick = { navController.navigate(LibraryRoutes.NEW) },
                onStickyClick = { navController.navigate(LibraryRoutes.STICKY) }
            )
        }

        composable(LibraryRoutes.RECENT) {
           // RecentScreen()
        }

        composable(LibraryRoutes.NEW) {
            NoteEditorScreen({},modifier)
        }

        composable(LibraryRoutes.STICKY) {
           // StickyScreen()
        }
    }
}