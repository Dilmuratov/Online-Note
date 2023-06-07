package com.example.onlinenote.domain.usecases.getallnotes

import com.example.onlinenote.data.models.NetworkNote
import com.example.onlinenote.domain.NetworkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetAllNotesUseCaseImpl(private val networkRepository: NetworkRepository) :
    GetAllNotesUseCase {
    override suspend fun execute(): Flow<List<NetworkNote>> = flow {
        networkRepository.getAllNotes().collect {
            emit(it)
        }
    }
}