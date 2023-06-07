package com.example.onlinenote.domain.usecases.deletenotes

import com.example.onlinenote.data.models.NetworkNote

interface DeleteNoteUseCase {

    suspend fun execute(networkNote: NetworkNote)
}