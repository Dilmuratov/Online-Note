package com.example.onlinenote.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.onlinenote.data.local.NoteDataBase
import com.example.onlinenote.data.models.Note
import com.example.onlinenote.domain.MainRepository
import androidx.lifecycle.LiveData as LiveData1

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MainRepository(NoteDataBase.getInstance(application).getNotesDao())

    private val _getAllNotesLiveData = MutableLiveData<List<Note>>()
    val getAllNotesLiveData: LiveData1<List<Note>> get() = _getAllNotesLiveData

    suspend fun getAllNotes() {
        _getAllNotesLiveData.value = repository.getAllNotes()
    }

    suspend fun addNote(note: Note) = repository.addNote(note)

    suspend fun updateNote(note: Note) = repository.updateNote(note)

    suspend fun searchNote(string: String) {
        _getAllNotesLiveData.value = repository.searchNote(string)
    }

    suspend fun deleteNote(note: Note) = repository.deleteNote(note)

    suspend fun getNoteById(id: Int) {
        _getAllNotesLiveData.value = repository.getNoteById(id)
    }

//    private val repository = MainRepository()

//    suspend fun updateNote(note: Note) = repository.updateNote(note)
//
//    suspend fun deleteNote(note: Note) = repository.deleteNote(note)
//
//    suspend fun searchNote(string: String) {
//        _getAllNotesLiveData.value = repository.searchNote(string)
//    }

}