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

    factory<GetAllNotesUseCase> {
        GetAllNotesUseCaseImpl(get())
    }

    factory<AddNoteUseCase> {
        AddNoteUseCaseImpl(get())
    }

    factory<DeleteNoteUseCase> {
        DeleteNoteUseCaseImpl(get())
    }

    factory<UpdateNoteUseCase> {
        UpdateNoteUseCaseImpl(get())
    }

    factory<SearchNotesUseCase> {
        SearchNotesUseCaseImpl(get())
    }
}