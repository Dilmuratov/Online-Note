package com.example.onlinenote.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.onlinenote.data.models.NetworkNote
import com.example.onlinenote.data.models.Note
import com.example.onlinenote.domain.NetworkRepository

class NetworkViewModel(application: Application): AndroidViewModel(application) {
    private val repository = NetworkRepository()

    suspend fun getAllNotes(): MutableLiveData<List<NetworkNote>> {
        return repository.getAllNotes()
    }

    suspend fun addNote(note: NetworkNote) {
        repository.addNote(note)
    }

    suspend fun searchNote(string: String): MutableLiveData<List<NetworkNote>> {
        return repository.searchNote(string)
    }

    suspend fun getIdByNote(networkNote: NetworkNote): String {
        return repository.getIdByNote(networkNote)
    }

//    suspend fun getNoteById(noteId: String) {
//        return repository.getNoteById(noteId)
//    }
}