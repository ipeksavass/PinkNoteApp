package com.ipeksavas.pinknote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.ipeksavas.pinknote.data.local.PinkNoteDatabase
import com.ipeksavas.pinknote.data.repository.NoteRepository
import com.ipeksavas.pinknote.presentation.notes.NoteViewModel
import com.ipeksavas.pinknote.presentation.notes.NotesViewModelFactory
import com.ipeksavas.pinknote.ui.theme.PinkNoteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = PinkNoteDatabase.getDatabase(applicationContext)
        //singleton dbyi oluşturuyoruz ve db instance'ını alıyoruz. Bu sayede db ile etkileşim kurabiliriz.
        
        val dao = db.noteDao()
        //dao ile etkileşim kurabiliriz. Dao, db ile etkileşim kurmamızı sağlayan bir arayüzdür.
        
        val repo = NoteRepository(dao)
        //dao'yu repository'ye enjekte ediyoruz (Manuel DI'ın ilk adımı)
        
        val noteViewModelFactory = NotesViewModelFactory(repo)
        //repository'yi ViewModelFactory'e enjekte ediyoruz (Manuel DI'ın ikinci adımı)
        
        val noteViewModel = ViewModelProvider(this, noteViewModelFactory)[NoteViewModel::class.java]
        //Factory aracılığıyla ViewModel'ımızı ayağa kaldırıyoruz
        
        enableEdgeToEdge()
        setContent {
            PinkNoteTheme {
            }
        }
    }
}
