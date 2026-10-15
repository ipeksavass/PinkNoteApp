package com.ipeksavas.pinknote.presentation.notes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteScreen(
    noteId: Int = -1,//new note -> -1, edit note -> noteId
    noteViewModel: NoteViewModel,
    onNavigateBack: () -> Unit,
    onSaveOrEditNoteClick: (String, String) -> Unit
){
    var title by remember { mutableStateOf("") }
    var content by remember {mutableStateOf("") }
    
    val notes by noteViewModel.notes.collectAsStateWithLifecycle()
    LaunchedEffect(noteId, notes){
        if(noteId != -1){
            val existingNote = notes.find{ it.id == noteId}
            existingNote?.let{
                title = it.title
                content = it.content
            }
        }
    }
    
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = {
                    Text( text = if(noteId == -1) "New Note 🌸" else "Edit Note 🌸" )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack){
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Go to the back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                    navigationIconContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ){ innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            
            OutlinedTextField(
                value = content,
                onValueChange = { content = it },
                label = { Text("Content")},
                modifier = Modifier.fillMaxWidth().height(200.dp)
            )
            
            Spacer( modifier = Modifier.weight(1f))
            
            Button(
                onClick = {
                    if ( title.isNotBlank() && content.isNotBlank()){
                        onSaveOrEditNoteClick(title, content)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ){
                Text(if(noteId == -1) "Save Note" else "Update Note" )
            }
        }
    }
}