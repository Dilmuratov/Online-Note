package com.example.onlinenote.domain.usecases.addnote

import com.example.onlinenote.data.models.NetworkNote
import com.example.onlinenote.domain.NetworkRepository

class AddNoteUseCaseImpl(private val networkRepository: NetworkRepository) : AddNoteUseCase {
    override suspend fun execute(networkNote: NetworkNote) {
        networkRepository.addNote(networkNote)
    }
}