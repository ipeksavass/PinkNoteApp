package com.ipeksavas.pinknote.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ipeksavas.pinknote.data.dao.NoteDao
import com.ipeksavas.pinknote.data.entity.NoteEntity
import kotlin.jvm.java

@Database(entities = [NoteEntity::class], version = 1)
abstract class PinkNoteDatabase: RoomDatabase() {
    
    abstract fun noteDao(): NoteDao //db ile etkileşim kurmak için dao'yu çağırıyoruz
    
    companion object{ //javadaki static keywordü
        @Volatile
        private var INSTANCE: PinkNoteDatabase ?= null
        //volatile: değişkenin değerinin her zaman main memory'den okunmasını sağlar. Bu sayede farklı threadler arasında veri tutarlılığı sağlanır.
        
        fun getDatabase(context: Context): PinkNoteDatabase{
            return INSTANCE?: synchronized(this){ //race condition'ı önlemek için synchronized kullanıyoruz. Bu sayede aynı anda birden fazla thread bu bloğa giremez.
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PinkNoteDatabase::class.java,
                    "pink_note_db"
                ).build()
                INSTANCE = instance
                instance //return satırı
            }
        }
    }
}