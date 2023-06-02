package com.example.onlinenote.di

import com.google.firebase.firestore.FirebaseFirestore
import org.koin.dsl.module

val networkModule = module {
    single<FirebaseFirestore> {
        FirebaseFirestore.getInstance()
    }
}