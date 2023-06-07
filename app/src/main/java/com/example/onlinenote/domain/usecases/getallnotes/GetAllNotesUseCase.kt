package com.example.onlinenote.domain.usecases.getallnotes

import com.example.onlinenote.data.models.NetworkNote
import kotlinx.coroutines.flow.Flow

interface GetAllNotesUseCase {
    suspend fun execute(): Flow<List<NetworkNote>>
}