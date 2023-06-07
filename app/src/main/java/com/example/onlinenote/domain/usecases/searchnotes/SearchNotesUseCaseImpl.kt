package com.example.onlinenote.domain.usecases.searchnotes

import com.example.onlinenote.data.models.NetworkNote
import com.example.onlinenote.domain.NetworkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchNotesUseCaseImpl(private val networkRepository: NetworkRepository) :
    SearchNotesUseCase {
    override suspend fun execute(string: String): Flow<List<NetworkNote>> = flow {
        networkRepository.searchNotes(string).collect {
            emit(it)
        }
    }
}