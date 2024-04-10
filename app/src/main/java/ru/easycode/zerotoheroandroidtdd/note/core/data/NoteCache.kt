package ru.easycode.zerotoheroandroidtdd.note.core.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteCache(
    @PrimaryKey val id: Long,
    val text: String,
    val folderId: Long
)