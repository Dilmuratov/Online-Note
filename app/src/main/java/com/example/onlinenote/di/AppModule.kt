package com.example.onlinenote.di

import com.example.onlinenote.presentation.NetworkViewModel
import com.example.onlinenote.presentation.NetworkViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel<NetworkViewModel> {
        NetworkViewModelImpl(
            getAllNotesUseCase = get(),
            addNoteUseCase = get(),
            searchNotesUseCase = get(),
            deleteNoteUseCase = get(),
            updateNoteUseCase = get()
        )
    }


}