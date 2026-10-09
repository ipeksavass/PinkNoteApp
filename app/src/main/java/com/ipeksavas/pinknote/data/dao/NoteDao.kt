package com.ipeksavas.pinknote.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ipeksavas.pinknote.data.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(entity: NoteEntity)
    
    @Query("SELECT * FROM notes")
    fun getAllNotes(): Flow<List<NoteEntity>>
    
    @Delete
    suspend fun deleteNote(entity: NoteEntity)
    
}