package com.ipeksavas.pinknote.presentation.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ipeksavas.pinknote.data.entity.NoteEntity
import com.ipeksavas.pinknote.data.repository.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NoteViewModel(
    private val noteRepository: NoteRepository
): ViewModel() {

    private val _notesState = MutableStateFlow<List<NoteEntity>>(emptyList())
    val notesState: StateFlow<List<NoteEntity>> = _notesState.asStateFlow()
    
    init{
        observeNotes()
    }
    
    private fun observeNotes(){ // ViewModel ilk oluşturulduğunda Room'daki akışı dinlemeye başlarız.
        viewModelScope.launch{
            noteRepository.getAllNotes().collect{ note->
                _notesState.value = note
            }
        }
    }
    
    fun insertNote(note: NoteEntity){
        viewModelScope.launch{
            noteRepository.insertNote(note)
        }
    }
    
    fun deleteNote(note: NoteEntity){
        viewModelScope.launch{
            noteRepository.deleteNote(note)
        }
    }
    
}