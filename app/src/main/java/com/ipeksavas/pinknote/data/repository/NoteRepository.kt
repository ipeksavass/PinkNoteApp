package com.ipeksavas.pinknote.data.repository

import com.ipeksavas.pinknote.data.dao.NoteDao
import com.ipeksavas.pinknote.data.entity.NoteEntity

class NoteRepository( private val noteDao: NoteDao) {
    
    fun getAllNotes() = noteDao.getAllNotes()
    
    suspend fun insertNote(noteEntity: NoteEntity) = noteDao.insertNote(noteEntity)
    
    suspend fun deleteNote(noteEntity: NoteEntity) = noteDao.deleteNote(noteEntity)
}