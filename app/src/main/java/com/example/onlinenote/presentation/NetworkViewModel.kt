package com.example.onlinenote.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.onlinenote.data.models.NetworkNote
import com.example.onlinenote.domain.NetworkRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.collect

class NetworkViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = NetworkRepository(FirebaseFirestore.getInstance())

    private val _getAllTodo = MutableLiveData<List<NetworkNote>>()
    val getAllTodo: LiveData<List<NetworkNote>> get() = _getAllTodo

    suspend fun getAllNotes() {
        repository.getAllNotes().collect() {
            _getAllTodo.value = it
        }
    }

    suspend fun addNote(note: NetworkNote) = repository.addNote(note)

    suspend fun deleteNote(note: NetworkNote) = repository.deleteNote(note)

    suspend fun updateNote(note: NetworkNote) = repository.updateNote(note)

    suspend fun searchNotes(string: String) {
        repository.searchNotes(string).collect() {
            _getAllTodo.value = it
        }
    }
}