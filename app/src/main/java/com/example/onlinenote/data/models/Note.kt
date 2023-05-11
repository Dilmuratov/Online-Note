package com.example.onlinenote.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var title: String = "",
    var text: String = "",
    var lastUpdatedData: String = ""
)
