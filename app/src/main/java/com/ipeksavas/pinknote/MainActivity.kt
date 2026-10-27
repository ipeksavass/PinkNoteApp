package com.ipeksavas.pinknote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.ipeksavas.pinknote.data.navigation.PinkNoteNavGraph
import com.ipeksavas.pinknote.presentation.notes.NoteViewModel
import com.ipeksavas.pinknote.ui.theme.PinkNoteTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val noteViewModel: NoteViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        enableEdgeToEdge()
        setContent {
            PinkNoteTheme{
                val navController = rememberNavController()
                PinkNoteNavGraph(
                    navController = navController,
                    noteViewModel = noteViewModel
                )
            }
        }
    }
}
