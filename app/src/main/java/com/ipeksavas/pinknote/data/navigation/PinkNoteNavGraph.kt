package com.ipeksavas.pinknote.data.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
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
                    noteViewModel = noteViewModel,
                    onAddNoteClick = {
                        navController.navigate("add_note_screen")
                    },
                    onEditNoteClick = { noteId->
                        navController.navigate("add_note_screen?noteId=$noteId")
                    }
                )
            }
        
        composable(
            route= "add_note_screen?noteId={noteId}",
            arguments= listOf(
                navArgument("noteId"){
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ){ backStackEntry ->
            val noteId= backStackEntry.arguments?.getInt("noteId") ?: -1
            
            AddNoteScreen(
                noteId = noteId,
                noteViewModel = noteViewModel,
                onNavigateBack = {
                    navController.popBackStack()
                },
                onSaveOrEditNoteClick = { title, content ->
                    if( noteId == -1 ){
                        val newNote = NoteEntity(title = title, content = content)
                        noteViewModel.insertNote(newNote)
                    }else{
                        val updateNote = NoteEntity(id = noteId, title = title, content = content)
                        noteViewModel.updateNote(updateNote)
                    }
                    navController.popBackStack()
                }
            )
        }
    }
}