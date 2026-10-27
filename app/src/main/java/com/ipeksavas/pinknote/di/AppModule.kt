package com.ipeksavas.pinknote.di

import android.content.Context
import androidx.room.Room
import com.ipeksavas.pinknote.data.dao.NoteDao
import com.ipeksavas.pinknote.data.local.PinkNoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    
    @Provides
    @Singleton
    fun providesDatabase(
        @ApplicationContext context: Context
    ): PinkNoteDatabase{
        return Room.databaseBuilder(
            context,
            PinkNoteDatabase::class.java,
            "pink_note_db"
        ).build()
    }
    
    @Provides
    @Singleton
    fun providesNoteDao(
        database: PinkNoteDatabase
    ): NoteDao {
        return database.noteDao()
    }
}