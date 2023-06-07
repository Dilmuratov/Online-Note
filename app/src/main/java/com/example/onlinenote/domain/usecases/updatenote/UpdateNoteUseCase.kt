package com.example.onlinenote.domain.usecases.updatenote

import com.example.onlinenote.data.models.NetworkNote

interface UpdateNoteUseCase {
    suspend fun execute(networkNote: NetworkNote)
}