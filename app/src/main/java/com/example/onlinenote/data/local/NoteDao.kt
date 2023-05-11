package com.example.onlinenote.data.local

import androidx.room.*
import com.example.onlinenote.data.models.Note

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes")
    suspend fun getAllNotes(): MutableList<Note>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM notes WHERE title LIKE '%' || :string || '%' OR text LIKE '%' || :string || '%' ")
    suspend fun searchNotes(string: String): MutableList<Note>

    @Query("SELECT * FROM notes WHERE id=:id")
    suspend fun getNoteById(id: Int): MutableList<Note>
}