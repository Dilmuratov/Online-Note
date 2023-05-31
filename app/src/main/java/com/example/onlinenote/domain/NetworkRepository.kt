package com.example.onlinenote.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.onlinenote.data.models.NetworkNote
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class NetworkRepository(private val db: FirebaseFirestore) {

    private val notesCollectionRef = db.collection("notes")
    private val _getAllTodo = MutableLiveData<NetworkNote>()
    val getAllTodo: LiveData<NetworkNote> get() = _getAllTodo

    suspend fun getAllNotes() = flow<List<NetworkNote>> {
        emit(notesCollectionRef.get().await().documents.mapNotNull {
            NetworkNote(
                it.id,
                it["title"].toString(),
                it["text"].toString(),
                it["lastUpdatedDate"].toString()
            )
        })
    }.catch { it.printStackTrace() }

    suspend fun addNote(note: NetworkNote) = notesCollectionRef.add(note)

    suspend fun deleteNote(note: NetworkNote) =
        notesCollectionRef.document(note.id.toString()).delete()

    suspend fun updateNote(note: NetworkNote) =
        notesCollectionRef.document(note.id.toString()).update(
            mapOf(
                "id" to note.id,
                "title" to note.title,
                "text" to note.text,
                "lastUpdatedDate" to note.lastUpdatedDate
            )
        )

    suspend fun searchNotes(string: String) = flow<List<NetworkNote>> {
        emit(notesCollectionRef.whereArrayContains("title", string)
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