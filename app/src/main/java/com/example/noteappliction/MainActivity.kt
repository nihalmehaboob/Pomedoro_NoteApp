package com.example.noteappliction

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.noteappliction.presentation.screens.HomeScreen
import com.example.noteappliction.ui.theme.NoteApplictionTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NoteApplictionTheme {
               // Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    HomeScreen()
                //}
            }
        }
    }
}


