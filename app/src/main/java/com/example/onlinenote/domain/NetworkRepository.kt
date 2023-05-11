package com.example.onlinenote.domain

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.onlinenote.data.models.NetworkNote
import com.google.firebase.firestore.FirebaseFirestore

class NetworkRepository {
    private val db = FirebaseFirestore.getInstance()
    private val notesCollectionRef = db.collection("notes")
    val notesLiveData = MutableLiveData<List<NetworkNote>>()

    suspend fun getAllNotes(): MutableLiveData<List<NetworkNote>> {

        notesCollectionRef.get()
            .addOnSuccessListener { result ->
                val notes = mutableListOf<NetworkNote>()
                for (document in result){
                    val note = document.toObject(NetworkNote::class.java)
                    notes.add(note)
                }
                notesLiveData.value = notes
            }
            .addOnFailureListener { e ->
                Log.w("TTTT", "Error getting documents.", e)
            }
        return notesLiveData
    }

    suspend fun addNote(note: NetworkNote){
        notesCollectionRef.add(note)
            .addOnSuccessListener { documentReference ->
                Log.d("TTTT", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("TTTT", "Error adding document", e)
            }
    }

    suspend fun searchNote(string: String): MutableLiveData<List<NetworkNote>> {
        val query = notesCollectionRef.whereGreaterThanOrEqualTo("title", string)

        query.get()
            .addOnSuccessListener { documents ->
                val notes = mutableListOf<NetworkNote>()
                for (document in documents){
                    val note = document.toObject(NetworkNote::class.java)
                    notes.add(note)
                }
                notesLiveData.value = notes
            }
            .addOnFailureListener { e ->
                Log.w("TTTT", "Error searching or getting documents", e)
            }
        return notesLiveData
    }

    suspend fun getIdByNote(networkNote: NetworkNote): String {
        var noteId = ""
        notesCollectionRef.get()
            .addOnSuccessListener { documents ->
                for (document in documents){
                    val note = document.toObject(NetworkNote::class.java)
                    if (note == networkNote){
                        noteId = document.id
                    }
                }
            }
            .addOnFailureListener { e ->
                Log.w("TTTT", "Failure!!!", e)
            }
        return noteId
    }

    suspend fun updateNoteById(noteId: String){
        lateinit var note: NetworkNote
        notesCollectionRef.document(noteId)
            .addSnapshotListener{document, e ->
                note = document?.toObject(NetworkNote::class.java)!!
            }
    }

//    suspend fun getNoteById(noteId: String){
//        notesCollectionRef.document()
//    }

//    suspend fun deleteNote(note: Note)
}