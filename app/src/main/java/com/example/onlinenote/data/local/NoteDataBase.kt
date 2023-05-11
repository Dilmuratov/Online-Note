package com.example.onlinenote.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.onlinenote.data.models.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDataBase : RoomDatabase() {

    abstract fun getNotesDao(): NoteDao

    companion object {
        const val DATABASE_NAME = "db_name"

        fun getInstance(context: Context): NoteDataBase {
            return Room.databaseBuilder(
                context,
                NoteDataBase::class.java,
                DATABASE_NAME
            ).fallbackToDestructiveMigration().build()
        }
    }
}