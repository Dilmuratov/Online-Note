package com.example.onlinenote.domain.usecases.addnote

import com.example.onlinenote.data.models.NetworkNote

interface AddNoteUseCase {
    suspend fun execute(networkNote: NetworkNote)
}