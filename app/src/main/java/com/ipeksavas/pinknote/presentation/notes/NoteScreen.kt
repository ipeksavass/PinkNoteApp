package com.ipeksavas.pinknote.presentation.notes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ipeksavas.pinknote.data.entity.NoteEntity
import com.ipeksavas.pinknote.ui.theme.NavyText

@Composable
fun NoteScreen(
    noteViewModel: NoteViewModel,
    onAddNoteClick: () -> Unit,
    onEditNoteClick: (Int) -> Unit
){
    val notes by noteViewModel.notes.collectAsStateWithLifecycle()
    //ViewModel'daki notes akışını Compose state'ine dönüştürüyorum.
    
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddNoteClick,
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ){
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add New Note"
                )
            }
        }
    ){ innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(6.dp)
        ){
            
            Spacer(modifier =  Modifier.height(16.dp))
            
            Text(
                text = "Pink Note 🌸",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                //MaterialTheme.typography.headlineMedium nesnesi değiştirilemez (immutable) bir yapıdır. Jetpack Compose'da .copy() fonksiyonu, "Mevcut özelliklerin hepsini koru, sadece benim belirttiğim alanları değiştirerek yeni bir kopya oluştur" anlamına gelir.
                color = MaterialTheme.colorScheme.primary
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ){
                items(items = notes) { note ->
                    NoteItem(
                        note = note,
                        onEditNoteClick = {onEditNoteClick(note.id)},
                        onDeleteNoteClick = {noteViewModel.deleteNote(it)}
                    )
                }
            }
        }
    }
}

@Composable
fun NoteItem(
    note: NoteEntity,
    onEditNoteClick: (Int) -> Unit,
    onDeleteNoteClick: (NoteEntity) -> Unit
){
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ){
        Box(
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(end = 40.dp)
            ){
                Text(
                    text = note.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = NavyText
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = note.content,
                    style = MaterialTheme.typography.bodyMedium,
                    color = NavyText
                )
            }
            
            IconButton(
                onClick = {onEditNoteClick(note.id)},
                modifier = Modifier.align(Alignment.TopEnd).size(24.dp)
            ){
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Note",
                    tint = NavyText
                )
            }
            
            IconButton(
                onClick = { onDeleteNoteClick(note)},
                modifier = Modifier.align(Alignment.BottomEnd).size(24.dp)
            ){
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete Note",
                    tint = Color.Red
                )
            }
        }
    }
}