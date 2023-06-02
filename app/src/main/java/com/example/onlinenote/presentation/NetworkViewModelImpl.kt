package com.example.onlinenote.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.onlinenote.data.models.NetworkNote
import com.example.onlinenote.data.repository.NetworkRepositoryImpl
import com.example.onlinenote.domain.NetworkRepository
import com.google.firebase.firestore.FirebaseFirestore

class NetworkViewModelImpl(private val repository: NetworkRepository) : NetworkViewModel(){


    private val _getAllTodo = MutableLiveData<List<NetworkNote>>()
    override val getAllTodo: LiveData<List<NetworkNote>> get() = _getAllTodo

    override suspend fun getAllNotes() {
        repository.getAllNotes().collect() {
            _getAllTodo.value = it
        }
    }

    override suspend fun addNote(networkNote: NetworkNote) = repository.addNote(networkNote)

    override suspend fun deleteNote(networkNote: NetworkNote) = repository.deleteNote(networkNote)

    override suspend fun updateNote(networkNote: NetworkNote) = repository.updateNote(networkNote)

    override suspend fun searchNotes(string: String) {
        repository.searchNotes(string).collect() {
            _getAllTodo.value = it
        }
    }
}