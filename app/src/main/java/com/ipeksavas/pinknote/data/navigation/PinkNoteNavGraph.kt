package com.ipeksavas.pinknote.data.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ipeksavas.pinknote.data.entity.NoteEntity
import com.ipeksavas.pinknote.presentation.notes.AddNoteScreen
import com.ipeksavas.pinknote.presentation.notes.NoteScreen
import com.ipeksavas.pinknote.presentation.notes.NoteViewModel

@Composable
fun PinkNoteNavGraph(
    navController: NavHostController,
    noteViewModel: NoteViewModel
){
    NavHost(
        navController = navController,
        startDestination = "note_screen"
        ){
            composable("note_screen"){
                NoteScreen(
                    viewModel = noteViewModel,
                    onAddNoteClick = {
                        navController.navigate("add_note_screen")
                    }
                )
            }
        
        composable("add_note_screen"){
            AddNoteScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onSaveNote = { title, content ->
                    val newNote = NoteEntity(title = title, content = content)
                    noteViewModel.insertNote(newNote)
                    navController.popBackStack()
                }
            )
        }
    }
}