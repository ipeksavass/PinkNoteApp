package com.ipeksavas.pinknote.presentation.notes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ipeksavas.pinknote.data.entity.NoteEntity

@Composable
fun NoteScreen(
    viewModel: NoteViewModel,
    onAddNoteClick: () -> Unit
){
    val notes by viewModel.notes.collectAsStateWithLifecycle()
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
                    imageVector = androidx.compose.material.icons.Icons.Default.Add,
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
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ){
                items(items = notes) { note ->
                    NoteItem(note = note)
                }
            }
        }
    }
}

@Composable
fun NoteItem(note: NoteEntity){
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ){
        Column(
            modifier = Modifier.padding(16.dp)
        ){
            Text(
                text = note.title,
                style = MaterialTheme.typography.titleMedium
            )
            
            Spacer(modifier = Modifier.height(4.dp))
            
            Text(
                text = note.content,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}