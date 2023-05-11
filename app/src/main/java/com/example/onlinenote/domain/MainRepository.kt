package com.example.onlinenote.domain

import com.example.onlinenote.data.local.NoteDao
import com.example.onlinenote.data.models.Note

class MainRepository(private val dao: NoteDao) {

    suspend fun getAllNotes() = dao.getAllNotes()

    suspend fun addNote(note: Note) = dao.addNote(note)

    suspend fun updateNote(note: Note) = dao.updateNote(note)

    suspend fun searchNote(string: String) = dao.searchNotes(string)

    suspend fun deleteNote(note: Note) = dao.deleteNote(note)

    suspend fun getNoteById(id: Int) = dao.getNoteById(id)

}