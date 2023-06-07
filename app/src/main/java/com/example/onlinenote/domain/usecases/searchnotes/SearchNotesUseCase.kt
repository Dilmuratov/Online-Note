package com.example.onlinenote.domain.usecases.searchnotes

import com.example.onlinenote.data.models.NetworkNote
import kotlinx.coroutines.flow.Flow

interface SearchNotesUseCase {
    suspend fun execute(string: String): Flow<List<NetworkNote>>
}