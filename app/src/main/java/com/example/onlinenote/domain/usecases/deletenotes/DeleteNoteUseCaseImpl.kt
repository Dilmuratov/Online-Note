package com.example.onlinenote.domain.usecases.deletenotes

import com.example.onlinenote.data.models.NetworkNote
import com.example.onlinenote.domain.NetworkRepository

class DeleteNoteUseCaseImpl(private val networkRepository: NetworkRepository) : DeleteNoteUseCase {
    override suspend fun execute(networkNote: NetworkNote) {
        networkRepository.deleteNote(networkNote)
    }
}