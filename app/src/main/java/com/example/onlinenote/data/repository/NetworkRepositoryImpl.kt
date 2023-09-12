package com.example.onlinenote.data.repository

import com.example.onlinenote.data.models.NetworkNote
import com.example.onlinenote.domain.NetworkRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class NetworkRepositoryImpl(db: FirebaseFirestore) : NetworkRepository {

    private val notesCollectionRef = db.collection("notes")

    override suspend fun getAllNotes() = flow {
        emit(notesCollectionRef.get().await().documents.mapNotNull {
            NetworkNote(
                it.id,
                it["title"].toString(),
                it["text"].toString(),
                it["lastUpdatedDate"].toString()
            )
        })
    }.catch { it.printStackTrace() }

    override suspend fun addNote(note: NetworkNote) {
        notesCollectionRef.add(note)
    }

    override suspend fun deleteNote(note: NetworkNote) {
        notesCollectionRef.document(note.id.toString()).delete()

    }

    override suspend fun updateNote(note: NetworkNote) {
        notesCollectionRef.document(note.id.toString()).update(
            mapOf(
                "id" to note.id,
                "title" to note.title,
                "text" to note.text,
                "lastUpdatedDate" to note.lastUpdatedDate
            )
        )
    }

    override suspend fun searchNotes(string: String) = flow {
        emit(notesCollectionRef.whereEqualTo("title", string)
            .get().await()
            .mapNotNull {
                NetworkNote(
                    it.id,
                    it["title"].toString(),
                    it["text"].toString(),
                    it["lastUpdatedDate"].toString()
                )
            })
    }.catch { it.printStackTrace() }
}


