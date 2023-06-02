package com.example.onlinenote.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.onlinenote.data.models.NetworkNote

abstract class NetworkViewModel : ViewModel() {

    abstract val getAllTodo: LiveData<List<NetworkNote>>

    abstract suspend fun getAllNotes()

    abstract suspend fun addNote(networkNote: NetworkNote)

    abstract suspend fun updateNote(networkNote: NetworkNote)

    abstract suspend fun deleteNote(networkNote: NetworkNote)

    abstract suspend fun searchNotes(string: String)

}