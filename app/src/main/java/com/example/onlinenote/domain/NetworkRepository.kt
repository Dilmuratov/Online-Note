package com.example.onlinenote.domain

import com.example.onlinenote.data.models.NetworkNote
import kotlinx.coroutines.flow.Flow

interface NetworkRepository {

    suspend fun getAllNotes(): Flow<List<NetworkNote>>

    suspend fun addNote(note: NetworkNote)

    suspend fun deleteNote(note: NetworkNote)

    suspend fun updateNote(note: NetworkNote)

    suspend fun searchNotes(string: String): Flow<List<NetworkNote>>
}