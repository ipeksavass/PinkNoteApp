package com.ipeksavas.pinknote.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ipeksavas.pinknote.data.dao.NoteDao
import com.ipeksavas.pinknote.data.entity.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class PinkNoteDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao //db ile etkileşim kurmak için dao'yu çağırıyoruz
    
}