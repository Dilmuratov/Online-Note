package com.example.onlinenote.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.onlinenote.data.models.NetworkNote
import com.example.onlinenote.domain.usecases.addnote.AddNoteUseCase
import com.example.onlinenote.domain.usecases.deletenotes.DeleteNoteUseCase
import com.example.onlinenote.domain.usecases.getallnotes.GetAllNotesUseCase
import com.example.onlinenote.domain.usecases.searchnotes.SearchNotesUseCase
import com.example.onlinenote.domain.usecases.updatenote.UpdateNoteUseCase

class NetworkViewModelImpl(
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val addNoteUseCase: AddNoteUseCase,
    private val searchNotesUseCase: SearchNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase
) : NetworkViewModel() {


    private val _getAllTodo = MutableLiveData<List<NetworkNote>>()
    override val getAllTodo: LiveData<List<NetworkNote>> get() = _getAllTodo

    override suspend fun getAllNotes() {
        getAllNotesUseCase.execute().collect {
            _getAllTodo.value = it
        }
    }

    override suspend fun addNote(networkNote: NetworkNote) {
        addNoteUseCase.execute(networkNote)
    }

    override suspend fun deleteNote(networkNote: NetworkNote) {
        deleteNoteUseCase.execute(networkNote)
    }

    override suspend fun updateNote(networkNote: NetworkNote) {
        updateNoteUseCase.execute(networkNote)
    }

    override suspend fun searchNotes(string: String) {
        searchNotesUseCase.execute(string).collect {
            _getAllTodo.value = it
        }
    }
}