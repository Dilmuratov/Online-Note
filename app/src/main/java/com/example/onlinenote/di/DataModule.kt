package com.example.onlinenote.di

import com.example.onlinenote.data.repository.NetworkRepositoryImpl
import com.example.onlinenote.domain.NetworkRepository
import org.koin.dsl.module

val dataModule = module {
    single<NetworkRepository> {
        NetworkRepositoryImpl(get())
    }
}