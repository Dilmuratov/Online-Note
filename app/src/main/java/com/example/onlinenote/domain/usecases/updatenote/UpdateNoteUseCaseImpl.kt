package com.example.onlinenote.domain.usecases.updatenote

import com.example.onlinenote.data.models.NetworkNote
import com.example.onlinenote.domain.NetworkRepository

class UpdateNoteUseCaseImpl(private val networkRepository: NetworkRepository) : UpdateNoteUseCase {
    override suspend fun execute(networkNote: NetworkNote) {
        networkRepository.updateNote(networkNote)
    }
}