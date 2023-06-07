package com.example.onlinenote.di

import com.example.onlinenote.data.repository.NetworkRepositoryImpl
import com.example.onlinenote.domain.NetworkRepository
import com.example.onlinenote.domain.usecases.addnote.AddNoteUseCase
import com.example.onlinenote.domain.usecases.addnote.AddNoteUseCaseImpl
import com.example.onlinenote.domain.usecases.deletenotes.DeleteNoteUseCase
import com.example.onlinenote.domain.usecases.deletenotes.DeleteNoteUseCaseImpl
import com.example.onlinenote.domain.usecases.getallnotes.GetAllNotesUseCase
import com.example.onlinenote.domain.usecases.getallnotes.GetAllNotesUseCaseImpl
import com.example.onlinenote.domain.usecases.searchnotes.SearchNotesUseCase
import com.example.onlinenote.domain.usecases.searchnotes.SearchNotesUseCaseImpl
import com.example.onlinenote.domain.usecases.updatenote.UpdateNoteUseCase
import com.example.onlinenote.domain.usecases.updatenote.UpdateNoteUseCaseImpl
import org.koin.dsl.module

val dataModule = module {
    single<NetworkRepository> {
        NetworkRepositoryImpl(get())
    }

    single<GetAllNotesUseCase> {
        GetAllNotesUseCaseImpl(get())
    }

    single<AddNoteUseCase> {
        AddNoteUseCaseImpl(get())
    }

    single<DeleteNoteUseCase> {
        DeleteNoteUseCaseImpl(get())
    }

    single<UpdateNoteUseCase> {
        UpdateNoteUseCaseImpl(get())
    }

    single<SearchNotesUseCase> {
        SearchNotesUseCaseImpl(get())
    }
}